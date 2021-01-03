package com.miniw.kitchen.processor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miniw.kitchen.manager.PageDataRepository;
import com.miniw.kitchen.pojo.entity.PageDataDo;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.service.PageDataService;
import com.miniw.kitchen.solr.service.SolrService;
import com.miniw.kitchen.strategy.AbstractStrategy;
import com.miniw.kitchen.strategy.StrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


/**
 * html 处理器
 * @author linsongjin
 */
@Slf4j
@Component
public class HtmlProcessor {

    @Resource
    private PageDataRepository repository;

    @Resource
    private StrategyContext context;

    @Resource
    private PageDataService pageDataService;

    @Resource
    private SolrService solrService;

    /**
     * 处理新增的数据
     */
    public void parsingAdd(String tableName, Integer dataId, String fullHtml){

        // 解析内容
        parsingDefaultContent(fullHtml);

        pageDataService.insert(tableName, dataId);

        // 将解析的新数据直接添加进索引，落库
        solrService.add(tableName, dataId);
    }


    /**
     * 处理编辑的数据
     */
    public void parsingEdit(String tableName, Integer dataId, String fullHtml){

        // 将旧数据删除
        parsingDel(tableName, dataId);

        // 数据落库
        parsingAdd(tableName, dataId, fullHtml);

        // 先删除，后添加进索引
        solrService.delete(tableName, dataId);
        solrService.add(tableName, dataId);
    }

    /**
     * 处理编辑的数据
     */
    public void parsingRemove(String tableName, String specialData){
        JSONArray dataArray = JSON.parseArray(specialData);
        if(dataArray != null && !dataArray.isEmpty()){
            for (int i = 0; i < dataArray.size(); i++) {
                // 父级节点
                JSONObject parentObject = dataArray.getJSONObject(i);
                JSONArray sonArray = parentObject.getJSONArray("children");
                if(sonArray != null && !sonArray.isEmpty()){
                    StringJoiner stringJoiner = new StringJoiner(",");
                    for (int j = 0; j < sonArray.size(); j++) {
                        JSONObject sonData = sonArray.getJSONObject(j);
                        Integer dataId = sonData.getInteger("id");

                        // 外部数据仍是较不可靠，需要获取已经拼接好的支持多级级联的父级标题
                        PageDataDo parentTitleDo = repository.getSideBarData(dataId);
                        if(parentTitleDo != null && parentTitleDo.getParentTitle() != null){
                            stringJoiner.add(parentTitleDo.getParentTitle());
                        }
                        stringJoiner.add(sonData.getString("label"));

                        // 修改数据的父级标题
                        pageDataService.changeTitle(tableName, dataId, stringJoiner.toString());

                        // 一个个更新效率太低，不如删除重新构建效率高
                        solrService.delete(tableName, dataId);
                        solrService.add(tableName, dataId);

                        stringJoiner.setEmptyValue(",");
                    }
                }
            }
        }
    }

    /**
     * 处理删除的数据
     */
    public void parsingDel(String tableName, Integer dataId){

        // 数据库数据物理删除
        pageDataService.delete(tableName, dataId);

        // 索引物理删除
        solrService.delete(tableName, dataId);
    }

    /**
     * 解析 主内容 DIV
     * @param fullHtml 富文本内容
     */
    protected void parsingDefaultContent(String fullHtml){
        Document contentDoc = Jsoup.parse(fullHtml);

        for (int i = 0; i < contentDoc.body().childrenSize(); i++) {
            Element node = contentDoc.body().children().get(i);
            processor(node);
        }

        repository.sysoPageData();
        repository.sysoPageContentData();
    }

    /**
     * Element 解析
     * 将通过策略进行解析
     * @param node Element
     */
    private void processor(Element node) {
        // 使用策略模式对不同的 Element 进行解析
        ElementTypeEnum elementTypeEnum = ElementTypeEnum.getByCss(node);
        if(elementTypeEnum != null){
            AbstractStrategy strategy = context.getElementStrategy(elementTypeEnum);
            strategy.process(node);
        }
    }
}

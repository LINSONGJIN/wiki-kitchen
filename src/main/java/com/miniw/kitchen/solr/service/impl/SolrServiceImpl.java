package com.miniw.kitchen.solr.service.impl;

import com.miniw.kitchen.pojo.entity.PageDataDo;
import com.miniw.kitchen.pojo.vo.PageDataVo;
import com.miniw.kitchen.service.PageDataService;
import com.miniw.kitchen.solr.service.SolrBaseService;
import com.miniw.kitchen.solr.service.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Solr 查询
 * @author linsongjin
 */
@Service
public class SolrServiceImpl extends SolrBaseService implements SolrService {

    @Resource
    private PageDataService pageDataService;

    @Override
    public void add(String tableName, Integer dataId) {
        List<PageDataDo> pageDataDoList = pageDataService.selectData(tableName, dataId);

        if(pageDataDoList != null && pageDataDoList.size() > 0){

            List<SolrInputDocument> documents = new ArrayList<>();
            for (int i = 0; i < pageDataDoList.size(); i++) {
                PageDataDo pageData = pageDataDoList.get(i);
                SolrInputDocument newData = new SolrInputDocument();
                newData.addField("id",  pageData.getId());
                newData.addField("title", pageData.getTitle());
                newData.addField("parentTitle", pageData.getParentTitle());
                newData.addField("url", pageData.getUrl());
                newData.addField("point", pageData.getPoint());
                newData.addField("content", pageData.getContent());
                newData.addField("tableName", tableName);
                newData.addField("dataId", dataId);
                newData.addField("typeId", pageData.getTypeId());
                documents.add(newData);
            }
            addData(WIKI_COLLEGE, documents);
        }
    }

    @Override
    public void edit(String tableName, Map<Integer, String> changeMap) {
        // 修改父级
    }

    @Override
    public void delete(String tableName, Integer dataId) {
        SolrQuery query = new SolrQuery();
        query.setQuery(String.format("dataId:%d AND tableName:%s", dataId, tableName));
        this.delete(WIKI_COLLEGE, query);
    }
}

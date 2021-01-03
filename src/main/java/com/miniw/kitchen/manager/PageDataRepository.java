package com.miniw.kitchen.manager;

import com.miniw.kitchen.pojo.entity.PageDataDo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 页面数据基础归类方法，存放地
 * @author linsongjin
 */
@Component
public class PageDataRepository {

    /** 页面内容的最上级标题 */
    private String topTitle;
    /** 标题 */
    private String title;
    /** 锚点 */
    private String point;

    /**
     * 结构树数据
     * SIDE_BAR ： {
 *       wiki id ：实体
     * }
     */
    private static final Map<Integer, PageDataDo> SIDE_BAR = new HashMap<>();

    /**
     * 标题对应页面数据
     * PAGE_TITLE : {
     *      h1标题1：页面实体,
     *      h1标题2：页面实体
     * }
     */
    private static final Map<String, PageDataDo> PAGE_TITLE = new HashMap<>();

    /**
     * 标题对应页面数据
     * PAGE_CONTENT_DATA : {
     *      标题1：[页面实体1,实体2...],
     *      标题2：[页面实体1,实体2...]
     *     ...
     * }
     */
    private static final Map<String, Map<String, List<PageDataDo>>> PAGE_CONTENT_DATA = new HashMap<>();


    /**
     * 一级标题，h1
     * 需要在解析 fullHtml 的时候就预埋下来，如果没有，则给默认值
     */
    public void setH1(String topTitle){
        this.topTitle = topTitle;
        PageDataDo h1PageData = new PageDataDo(topTitle, point);
        PAGE_TITLE.putIfAbsent(topTitle, h1PageData);
        PAGE_CONTENT_DATA.computeIfAbsent(topTitle, k -> new HashMap<>());
    }

    /**
     * 定内容的标题
     * @param title 除一级标题外的标题
     */
    public void fixedTitle(String title) {
        this.title = title;
        PAGE_CONTENT_DATA.get(topTitle).computeIfAbsent(title, k -> new LinkedList<>());
    }

    public void fixedPoint(String point){
        this.point = point;
    }

    /**
     * 添加内容
     * @param context 内容
     */
    public void addContent(String context) {
        PageDataDo pageDataDo = new PageDataDo();
        pageDataDo.setParentTitle(this.topTitle);
        pageDataDo.setTitle(this.title);
        pageDataDo.setContent(context);
        pageDataDo.setPoint(this.point);
        PAGE_CONTENT_DATA.get(topTitle).get(title).add(pageDataDo);
    }

    public void sysoPageData(){
        PAGE_TITLE.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v.toString());
        });
    }

    public void sysoPageContentData(){
        PAGE_CONTENT_DATA.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v.toString());
        });
    }

    public void sysoSideBarData() {
        SIDE_BAR.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v.toString());
        });
    }

    public Map<String, PageDataDo> getPageData(){
        return PAGE_TITLE;
    }

    public Map<String, List<PageDataDo>> getPageContentData(String parentTitle){
        return PAGE_CONTENT_DATA.get(parentTitle);
    }

    public void removePageContentData(String key){
        PAGE_CONTENT_DATA.remove(key);
    }

    public void addSideBarData(PageDataDo pageDataDo) {
       SIDE_BAR.put(pageDataDo.getDataId(), pageDataDo);
    }

    public PageDataDo getSideBarData(Integer id){
        return SIDE_BAR.get(id);
    }
}

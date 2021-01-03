package com.miniw.kitchen.solr.service;

import com.miniw.kitchen.pojo.vo.PageDataVo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author linsongjin
 */
public interface SolrService {

    /**
     * 删除索引数据
     * @param tableName 表名
     * @param dataId    表名数据id
     */
    void delete(String tableName, Integer dataId);

    /**
     * 增加索引数据
     * @param tableName 表名
     * @param dataId    表名数据id
     */
    void add(String tableName, Integer dataId);

    /**
     * 修改索引数据
     * @param changeMap 要进行修改的数据，key为数据id，value为新的标题
     */
    void edit(String tableName, Map<Integer, String> changeMap);
}

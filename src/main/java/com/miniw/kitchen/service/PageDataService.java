package com.miniw.kitchen.service;

import com.miniw.kitchen.pojo.entity.PageDataDo;

import java.util.List;
import java.util.Map;

public interface PageDataService {

    /** 新增 */
    void insert(String tableName, Integer dataId);

    /** 删除 */
    void delete(String tableName, Integer dataId);

    /** 修改父级标题 */
    void changeTitle(String tableName, Integer dataId, String newTitle);

    /** 查询数据 */
    List<PageDataDo> selectData(String tableName, Integer dataId);
}

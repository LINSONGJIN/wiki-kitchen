package com.miniw.kitchen.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据实体 DO
 */
@Data
@NoArgsConstructor
public class PageDataDo {

    /** id */
    private Integer id;

    /** 对应侧边栏 */
    private String parentTitle;

    /** 标题 */
    private String title;

    /** 对应 URL */
    private String url;

    /** HTML锚点 */
    private String point;

    /** 正文内容，真正进行索引构建的数据 */
    private String content;

    private String tableName;
    private Integer dataId;
    private Integer typeId;


    public PageDataDo(String title, String point) {
        this.title = title;
        this.point = point;
        this.content = title;
    }
}

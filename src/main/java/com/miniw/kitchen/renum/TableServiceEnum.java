package com.miniw.kitchen.renum;

/**
 * 数据表 枚举
 * @author linsongjin
 */
public enum TableServiceEnum {

    /** 标题 */
    RECOMMEND("recommend"),
    RECOMMEND_TYPE("recommend_type"),
    INDEX("index_record"),
    WIKI("wiki")
    ;

    private String name;

    TableServiceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


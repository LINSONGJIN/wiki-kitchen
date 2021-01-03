package com.miniw.kitchen.service;

/**
 * DAO 抽象类
 * dao 需要用的通用方法都应该存储在这里
 * @author linsongjin
 */
public abstract class AbstractService {

    /**
     * 获取富文本内容
     * @param id 数据ID
     * @return 富文本内容
     */
    abstract public String getFullHtml(Integer id);

}

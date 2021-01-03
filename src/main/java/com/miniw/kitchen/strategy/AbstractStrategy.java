package com.miniw.kitchen.strategy;

import com.miniw.kitchen.manager.PageDataRepository;
import org.jsoup.nodes.Element;

import javax.annotation.Resource;

/**
 * 策略抽象类
 */
public abstract class AbstractStrategy {

    @Resource
    protected PageDataRepository repository;

    abstract public void process(Element doc);

}

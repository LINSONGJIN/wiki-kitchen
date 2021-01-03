package com.miniw.kitchen.strategy.impl;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.strategy.AbstractStrategy;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

/**
 * 代码块处理类
 * @author linsongjin
 */
@Service
@ElementTypeAnnotation(elementType = ElementTypeEnum.CODE)
public class CodeElementStrategy extends AbstractStrategy {

    @Override
    public void process(Element doc) {
        // TODO 处理代码块
    }
}

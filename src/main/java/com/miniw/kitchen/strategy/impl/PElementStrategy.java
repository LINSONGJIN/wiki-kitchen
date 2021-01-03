package com.miniw.kitchen.strategy.impl;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.strategy.AbstractStrategy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * p 标签处理类
 * @author linsongjin
 */
@Service
@ElementTypeAnnotation(elementType = ElementTypeEnum.P)
public class PElementStrategy extends AbstractStrategy {

    @Override
    public void process(Element doc) {

        // 如果包含 IMG 标签，需要 remove
        Elements imgElement = doc.select("img");
        if(imgElement.size() > 0){
            imgElement.remove();
        }

        // 存储数据
        String context = doc.text();
        repository.addContent(context);
    }
}

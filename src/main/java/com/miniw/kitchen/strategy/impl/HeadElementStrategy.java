package com.miniw.kitchen.strategy.impl;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.strategy.AbstractStrategy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * h1 数据处理类
 * @author linsongjin
 */
@Service
@ElementTypeAnnotation(elementType = ElementTypeEnum.HEAD)
public class HeadElementStrategy extends AbstractStrategy {

    @Override
    public void process(Element doc) {

        Elements a  = doc.select("a");
        String id = "";
        String href = "";
        if(a.size() > 0){
            id = doc.id();
            href = a.attr("href");
            repository.fixedPoint(href);
            a.remove();
        }

        String content = doc.text().trim();
        if(doc.tagName().equals("h1")){
            // 确定当前页面的 h1 标题
            repository.setH1(content);
        } else {
            // 确定普通标题
            repository.fixedTitle(id);
            repository.addContent(content);
        }
    }
}

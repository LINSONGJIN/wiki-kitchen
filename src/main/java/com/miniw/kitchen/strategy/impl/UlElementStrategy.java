package com.miniw.kitchen.strategy.impl;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.strategy.AbstractStrategy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * tip 提醒框内数据处理类
 * @author linsongjin
 */
@Service
@ElementTypeAnnotation(elementType = ElementTypeEnum.UL)
public class UlElementStrategy extends AbstractStrategy {

    @Override
    public void process(Element doc) {
        if(doc.tagName().equals("ol")){
            processOl(doc);
        } else {
            processUl(doc);
        }
    }

    /**
     * 处理 ol 有序列表
     */
    private void processOl(Element doc){
        for (int i = 0; i < doc.childrenSize(); i++) {
            Element child = doc.child(i);
            if("hr".equals(child.tagName())){
                continue;
            }

            Elements childOl = child.select("ol");
            if(childOl.size() > 0){
                processOl(childOl.get(0));
                childOl.get(0).remove();
            }


            String context = child.text();
            // 将内容添加在索引对象中去
            repository.addContent(context);
        }
    }

    /**
     * 处理 ul 无序列表
     */
    private void processUl(Element doc){
        for (int i = 0; i < doc.childrenSize(); i++) {
            Element child = doc.child(i);
            if("hr".equals(child.tagName())){
                continue;
            }

            Elements childUl = child.select("ul");
            if(childUl.size() > 0){
                processUl(childUl.get(0));
                childUl.get(0).remove();
            }

            String context = child.text();
            // 将内容添加在索引对象中去
            repository.addContent(context);

        }
    }
}

package com.miniw.kitchen.strategy.impl;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.strategy.AbstractStrategy;
import com.miniw.kitchen.strategy.StrategyContext;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.miniw.kitchen.consts.SelectConst.TIP_CODE;

/**
 * tip 提醒框内数据处理类
 * @author linsongjin
 */
@Service
@ElementTypeAnnotation(elementType = ElementTypeEnum.TIP)
public class TipElementStrategy extends AbstractStrategy {


    @Resource
    private StrategyContext context;

    @Override
    public void process(Element doc) {
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < doc.childrenSize(); i++) {
            Element child = doc.child(i);
            if("hr".equals(child.tagName())){
                continue;
            }

            Elements tip_code = doc.select(TIP_CODE);
            if(tip_code.size() > 0){
                tip_code.remove();
            }

            // 使用策略模式对不同的 Element 进行解析
            ElementTypeEnum elementTypeEnum = ElementTypeEnum.getByCss(child);
            if(elementTypeEnum != null){
                AbstractStrategy strategy = context.getElementStrategy(elementTypeEnum);
                strategy.process(child);
                child.remove();
            } else {
                // 将内容添加在索引对象中去
                sbd.append(i==0?"":":").append(child.text());
            }

        }
        repository.addContent(sbd.toString());
    }
}

package com.miniw.kitchen.strategy.impl;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.renum.ElementTypeEnum;
import com.miniw.kitchen.strategy.AbstractStrategy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * table 数据处理类
 * @author linsongjin
 */
@Service
@ElementTypeAnnotation(elementType = ElementTypeEnum.TABLE)
public class TableElementStrategy extends AbstractStrategy {

    @Override
    public void process(Element doc) {
        for (int i = 0; i < doc.childrenSize(); i++) {
            Elements trs = doc.select("tbody tr");
            if(trs.size() > 0){

                StringBuilder sbd;
                for (int j = 0; j < trs.size(); j++) {
                    Element tr = trs.get(j);
                    if(tr.childrenSize() > 0){
                        sbd = new StringBuilder();
                        for (int k = 0; k < tr.childrenSize(); k++) {
                            Element td = tr.child(k);
                            sbd.append(k == 0?"":":").append(td.text());
                        }
                        repository.addContent(sbd.toString());
                    }
                }
            }
        }
    }
}

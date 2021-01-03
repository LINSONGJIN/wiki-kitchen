package com.miniw.kitchen.processor;

import com.miniw.kitchen.service.AbstractService;
import com.miniw.kitchen.strategy.StrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 数据处理器
 * @author linsongjin
 */
@Slf4j
@Component
public class DataProcessor {

    @Resource
    private StrategyContext strategyContext;

    /**
     * 获取FullHtml
     * @param tableName 表名
     * @param dataId    数据ID
     * @return 富文本内容
     */
    public String getFullHtml(String tableName, Integer dataId) {
        AbstractService service = strategyContext.getServiceStrategy(tableName);
        if(service != null){
            return service.getFullHtml(dataId);
        }
        return null;
    }
}

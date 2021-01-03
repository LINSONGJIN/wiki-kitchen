package com.miniw.kitchen.strategy;

import com.miniw.kitchen.annotation.ElementTypeAnnotation;
import com.miniw.kitchen.annotation.TableServiceAnnotation;
import com.miniw.kitchen.service.AbstractService;
import com.miniw.kitchen.renum.ElementTypeEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计模式：策略模式
 * 策略上下文，会在项目启动时候收集被注解标记的各种策略类
 * 目前有：
 *  1.html 元素策略类
 *  2.获取数据策略类
 *
 * @author linsongjin
 */
@Slf4j
@Component
public class StrategyContext implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /** Element DOM 解析类 MAP */
    private Map<ElementTypeEnum, AbstractStrategy> strategyMap;

    /** Data Service 执行类 MAP */
    private Map<String, AbstractService> daoMap;

    @SneakyThrows
    public AbstractStrategy getElementStrategy(ElementTypeEnum elementTypeEnum){
        if(elementTypeEnum == null){
            log.error("not found enum ");
            throw new IllegalAccessException("not found enum");
        }

        if(CollectionUtils.isEmpty(strategyMap)){
            log.error("strategy map should not be empty");
        }

        AbstractStrategy clazz = strategyMap.get(elementTypeEnum);
        if(clazz == null){
            log.error("strategy type not found: {}", elementTypeEnum.getCode());
        }

        return clazz;
    }

    @SneakyThrows
    public AbstractService getServiceStrategy(String tableName){

        if(CollectionUtils.isEmpty(daoMap)){
            log.error("strategy map should not be empty");
        }

        AbstractService clazz = daoMap.get(tableName);
        if(clazz == null){
            log.error("strategy type not found: {}", tableName);
        }

        return clazz;
    }


    @Override
    public void afterPropertiesSet() {
        strategyMap = new HashMap<>();
        Map<String,Object> map= applicationContext.getBeansWithAnnotation(ElementTypeAnnotation.class);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            Object obj = entry.getValue();
            strategyMap.put(obj.getClass().getAnnotation(ElementTypeAnnotation.class).elementType(), (AbstractStrategy) obj);
        }

        // auto find Service
        daoMap = new HashMap<>();
        Map<String,Object> services = applicationContext.getBeansWithAnnotation(TableServiceAnnotation.class);
        for(Map.Entry<String,Object> entry : services.entrySet()){
            Object obj = entry.getValue();
            daoMap.put(obj.getClass().getAnnotation(TableServiceAnnotation.class).value().getName() ,(AbstractService) obj);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

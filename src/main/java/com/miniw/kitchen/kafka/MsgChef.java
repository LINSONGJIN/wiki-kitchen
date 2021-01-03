package com.miniw.kitchen.kafka;


import com.miniw.kitchen.manager.SidebarManager;
import com.miniw.kitchen.processor.DataProcessor;
import com.miniw.kitchen.processor.HtmlProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.miniw.kitchen.consts.DataOperationConst.*;
import static com.miniw.kitchen.consts.KafkaMsgConst.*;

/**
 * 真正意义上的项目入口
 * 会监听 kafka 指定 topic，并处理消息
 * Kafka 消息消费，指令执行
 * @author linsongjin
 */
@Component
public class MsgChef extends AbstractKafkaConsumer {

    @Resource
    private HtmlProcessor handler;

    @Resource
    private DataProcessor dataHandler;

    @Resource
    private SidebarManager sidebarManager;

    @Override
    protected void dealMsg(ConsumerRecord<Integer, String> record) {
        String[] valueArr = record.value().split(DELIMITER);

        String fullHtml;
        switch (valueArr[INDEX_OPT]){
            case OPT_ADD :
                // 对页面内容进行解析，进行页面生成，索引重构
                fullHtml = dataHandler.getFullHtml(valueArr[INDEX_TABLE_NAME], record.key());
                handler.parsingAdd(valueArr[INDEX_TABLE_NAME], record.key(), fullHtml);
                return;
            case OPT_UPDATE :
                // 对页面内容进行解析，进行页面生成，索引重构
                fullHtml = dataHandler.getFullHtml(valueArr[INDEX_TABLE_NAME], record.key());
                handler.parsingEdit(valueArr[INDEX_TABLE_NAME], record.key(), fullHtml);
                return;
            case OPT_TREE_CHANGE :
                sidebarManager.afterPropertiesSet();
                // 同步修改上级
                handler.parsingRemove(valueArr[INDEX_TABLE_NAME], valueArr[INDEX_DATA]);
                return;
            case OPT_DEL :
                handler.parsingDel(valueArr[INDEX_TABLE_NAME], record.key());
                return;
            default: break;
        }

    }
}

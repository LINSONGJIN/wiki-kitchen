package com.miniw.kitchen.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

/**
 * kafka 消息消费者抽象类
 * @author linsongjin
 */
public abstract class AbstractKafkaConsumer {

    /** kafka topic - wiki开发者学院专用 */
    protected static final String KAFKA_TOPIC = "wikiCollege";

    @KafkaListener(topics = {KAFKA_TOPIC})
    private void listener(ConsumerRecord<Integer, String> record){
        dealMsg(record);
    }

    protected abstract void dealMsg(ConsumerRecord<Integer, String> record);

}

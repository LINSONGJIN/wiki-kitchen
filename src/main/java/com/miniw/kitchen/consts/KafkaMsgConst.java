package com.miniw.kitchen.consts;

/**
 * Kafka 消息中的数据常量
 * @author linsongjin
 */
public class KafkaMsgConst {

    /** 消息内容中的分隔符 */
    public static final String DELIMITER = ":";

    /** 消息内容中数据表名的索引位 */
    public static final Integer INDEX_TABLE_NAME = 0;

    /** 消息内容中操作代码的索引位 */
    public static final Integer INDEX_OPT = 1;

    /** 消息内容中额外附带的信息 */
    public static final Integer INDEX_DATA = 2;

}

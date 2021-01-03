package com.miniw.kitchen.pojo.dto;

import lombok.Data;

/**
 * 富文本 DTO
 * @author linsongjin
 */
@Data
public class FullHtmlDTO {

    private Integer id;
    /** 富文本内容 */
    private String content;
    /** 生成的文件名称 */
    private String fileName;
    /** 存放HTML路径 */
    private String dirUrl;
    /** 跳转链接 */
    private String url;

    private Long createTime;
    private String creator;
    private String creatorId;

    private Long updateTime;
    private String updater;
    private String updaterId;
}

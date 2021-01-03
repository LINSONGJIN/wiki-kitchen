package com.miniw.kitchen.pojo.dto;

import lombok.Data;

/**
 * wiki 数据 DTO
 * @author linsongjin
 */
@Data
public class WikiDTO extends AbstractRecordDTO {

    /** 富文本内容ID */
    private FullHtmlDTO fullHtml;

    /** 跳转链接 */
    private String url;

    /** 0百科；1创作案例 */
    private Integer type;

    /** 父类ID */
    private Integer pid;

    @Override
    protected void specialUpdate(Object optObj) { }
}

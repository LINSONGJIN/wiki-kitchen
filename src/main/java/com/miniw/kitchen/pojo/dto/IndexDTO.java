package com.miniw.kitchen.pojo.dto;

import lombok.Data;

/**
 * 欢迎页 DTO
 * @author linsongjin
 */
@Data
public class IndexDTO extends AbstractRecordDTO{

    /** 富文本 */
    private FullHtmlDTO fullHtml;

    @Override
    protected void specialUpdate(Object optObj) {
    }
}

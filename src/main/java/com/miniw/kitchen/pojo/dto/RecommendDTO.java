package com.miniw.kitchen.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 推荐数据
 * @author linsongjin
 */
@Data
@NoArgsConstructor
public class RecommendDTO extends AbstractRecordDTO implements Cloneable {

    /** 跳转URL */
    private String url;
    /** 所属推荐页 */
    private Integer typeId;
    /** 富文本内容 */
    private FullHtmlDTO fullHtml;

    public RecommendDTO(Integer id) {
        setId(id);
    }

    @Override
    public RecommendDTO clone() {
        RecommendDTO newData = new RecommendDTO();
        newData.setName(getName());
        newData.setUrl(getUrl());
        return newData;
    }

    @Override
    protected void specialUpdate(Object obj) {
        RecommendDTO optObj = (RecommendDTO) obj;
        if(optObj.getUrl() != null){ this.url = optObj.getUrl(); }
        if(optObj.getTypeId() != null){ this.typeId = optObj.getTypeId(); }
    }
}

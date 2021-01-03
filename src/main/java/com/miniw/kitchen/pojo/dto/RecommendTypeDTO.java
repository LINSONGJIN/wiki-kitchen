package com.miniw.kitchen.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 推荐位类型
 * @author linsongjin
 */
@Data
@NoArgsConstructor
public class RecommendTypeDTO extends AbstractRecordDTO implements Cloneable {

    /** 跳转连接 */
    private String url;
    /** 图标 */
    private String icon;
    /** 类型: 0正常，1开发者专辑 */
    private Integer type;

    /** 富文本 */
    private FullHtmlDTO fullHtml;

    /** 包含的推荐位记录 */
    private List<RecommendDTO> recommends;

    @Override
    public RecommendTypeDTO clone() {
        RecommendTypeDTO newData = new RecommendTypeDTO();
        newData.setName(getName());
        newData.setIcon(icon);
        newData.setRecommends(new LinkedList<>());
        return newData;
    }

    @Override
    protected void specialUpdate(Object obj) {
        RecommendTypeDTO optObj = (RecommendTypeDTO) obj;
        if(optObj.getUrl() != null){ this.url = optObj.getUrl(); }
        if(optObj.getIcon() != null){ this.icon = optObj.getIcon(); }
        if(optObj.getType() != null){ this.type = optObj.getType(); }
    }
}

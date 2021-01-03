package com.miniw.kitchen.pojo.vo;

import com.miniw.kitchen.pojo.entity.PageDataDo;
import lombok.Data;

/**
 * 符合数据实体 VO
 */
@Data
public class PageDataVo {

    /** 对应侧边栏 */
    private String parentTitle;

    /** 标题 */
    private String title;

    /** 锚点 */
    private String point;

    /** 对应 URL */
    private String url;

    /** 符合条件的内容 */
    private String content;

    public PageDataVo(PageDataDo pageDataDo) {
        this.parentTitle = pageDataDo.getParentTitle();
        this.title = pageDataDo.getTitle();
        this.point = pageDataDo.getPoint();
        this.url = pageDataDo.getUrl();
        this.content = pageDataDo.getContent();
    }
}

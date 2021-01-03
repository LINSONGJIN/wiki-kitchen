package com.miniw.kitchen.renum;

import org.jsoup.nodes.Element;

/**
 * 文档中需要解析的数据枚举
 * @author linsongjin
 */
public enum ElementTypeEnum {

    /** 标题 */
    HEAD("h1,h2,h3,h4,h5,h6", ""),
    /** 提示框 */
    TIP("div", "custom-block tip"),
    UL("ul,ol", ""),
    /** p 标签 */
    P("p", ""),
    /** 表格 */
    TABLE("table", ""),
    /** 代码块 */
    CODE("div", "language-lua extra-class")
    ;

    private String code;
    private String css;

    ElementTypeEnum(String code, String css) {
        this.code = code;
        this.css = css;
    }

    public String getCode() {
        return code;
    }

    public String getCss() {
        return css;
    }

    public static ElementTypeEnum getByCss(Element node){
        String css = node.attr("class");
        String tagName = node.tagName();
        for (ElementTypeEnum e : ElementTypeEnum.values()) {
            String[] codes = e.getCode().split(",");
            // 支持相同类型标签的统一处理
            for (int i = 0; i < codes.length; i++) {
                if(codes[i].equals(tagName) && e.getCss().equals(css)){
                    return e;
                }
            }
        }
        return null;
    }
}

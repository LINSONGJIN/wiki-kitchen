package com.miniw.kitchen.consts;

/**
 * 样式常量
 * @author linsongjin
 */
public class SelectConst {

    /** 主要抓取内容 CONTENT */
    public static final String DEFAULT_CONTENT = "div.theme-default-content.content__default";

    /** 侧边栏整体 UL */
    public static final String SIDEBAR_SECTION = "section.sidebar-group.depth-0";

    /** 侧边栏被选中的 li */
    public static final String SIDEBAR_LI_ACTIVE = "a.active.sidebar-link";

    /** 侧边栏被选中的父标题 */
    public static final String SIDEBAR_P_OPEN = "p.sidebar-heading.open";

    /** 侧边栏: 归类标题 */
    public static final String SIDEBAR_SPAN_TITLE = "p span";

    public static final String SECTION_UI_LI_LINK_A = "ul > li > a.sidebar-link";
    /** section 中被选中的 a 标签 */
    public static final String SECTION_UI_LI_ACTIVE_LINK_A = "ul > li > a.active.sidebar-link";
    /** section > ul > li > a */
    public static final String SECTION_UI_LI_A = "ul > li.sidebar-sub-header > a";


    /** tip 中的 代码块 */
    public static final String TIP_CODE = "div[class*=language-]";

    /** 结尾跳转 URL */
    public static final String PAGE_NAV = "div.page-nav span.next a";


}

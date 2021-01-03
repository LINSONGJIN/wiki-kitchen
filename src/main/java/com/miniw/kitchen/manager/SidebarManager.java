package com.miniw.kitchen.manager;

import com.miniw.kitchen.dao.WikiDao;
import com.miniw.kitchen.pojo.dto.WikiDTO;
import com.miniw.kitchen.pojo.entity.PageDataDo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.StringJoiner;

/**
 * 侧边栏管理者
 * @author linsongjin
 */
@Component
public class SidebarManager implements InitializingBean {

    @Resource
    private PageDataRepository repository;

    @Resource
    private WikiDao wikiDao;

    @Override
    public void afterPropertiesSet() {
        initSideBarData();
    }

    /** 构建侧边栏结构数据 */
    private void initSideBarData() {
        List<WikiDTO> sonWiki = wikiDao.selectAllData();
        if(sonWiki != null){
            for (int i = 0; i < sonWiki.size(); i++) {
                WikiDTO wikiDTO = sonWiki.get(i);

                PageDataDo pageDataDo = new PageDataDo();
                if(wikiDTO.getPid() != null){
                    String parentTitle = findParent(wikiDTO.getPid());
                    pageDataDo.setParentTitle(parentTitle);
                }
                // wiki 数据本身的 id
                pageDataDo.setDataId(wikiDTO.getId());
                // 标题
                pageDataDo.setTitle(wikiDTO.getName());
                // 分类id
                pageDataDo.setTypeId(wikiDTO.getType());

                repository.addSideBarData(pageDataDo);
            }
        }
    }

    /**
     * 递归查找父级，并返回拼接好的所有父级的标题
     */
    private String findParent(Integer pid){
        StringJoiner stringJoiner = new StringJoiner(",");
        WikiDTO selectWiki = new WikiDTO();
        selectWiki.setPid(pid);
        List<WikiDTO> parentsWiki = wikiDao.selectData(selectWiki);
        if(parentsWiki != null && parentsWiki.size() > 0){
            if(parentsWiki.get(0) != null && parentsWiki.get(0).getPid() != null){
                stringJoiner.add(findParent(parentsWiki.get(0).getPid()));
            }
            stringJoiner.add(parentsWiki.get(0).getName());
        }

        return stringJoiner.toString();
    }
}

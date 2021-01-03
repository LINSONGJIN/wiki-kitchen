package com.miniw.kitchen.service.impl;

import com.miniw.kitchen.annotation.TableServiceAnnotation;
import com.miniw.kitchen.dao.WikiDao;
import com.miniw.kitchen.pojo.dto.WikiDTO;
import com.miniw.kitchen.renum.TableServiceEnum;
import com.miniw.kitchen.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Wiki Service Impl
 * @author linsongjin
 */
@Service
@TableServiceAnnotation(value = TableServiceEnum.WIKI)
public class WikiServiceImpl extends AbstractService {

    @Resource
    private WikiDao dao;

    @Override
    public String getFullHtml(Integer id) {
        WikiDTO wiki = dao.selectById(id);
        if(wiki != null){
            return wiki.getFullHtml().getContent();
        }
        return null;
    }
}

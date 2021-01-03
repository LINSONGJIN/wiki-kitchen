package com.miniw.kitchen.service.impl;

import com.miniw.kitchen.annotation.TableServiceAnnotation;
import com.miniw.kitchen.dao.FullHtmlDao;
import com.miniw.kitchen.dao.RecommendDao;
import com.miniw.kitchen.dao.RecommendTypeDao;
import com.miniw.kitchen.renum.TableServiceEnum;
import com.miniw.kitchen.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 推荐页 Service
 * @author linsongjin
 */
@Service
@TableServiceAnnotation(value = TableServiceEnum.RECOMMEND_TYPE)
public class RecommendTypeServiceImpl extends AbstractService {

    @Resource
    private RecommendTypeDao dao;
    @Resource
    private RecommendDao recommendDao;
    @Resource
    private FullHtmlDao fullHtmlDao;

    @Override
    public String getFullHtml(Integer id) {
        return null;
    }
}


package com.miniw.kitchen.service.impl;

import com.miniw.kitchen.annotation.TableServiceAnnotation;
import com.miniw.kitchen.dao.FullHtmlDao;
import com.miniw.kitchen.dao.RecommendDao;
import com.miniw.kitchen.renum.TableServiceEnum;
import com.miniw.kitchen.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 推荐页 Service Impl
 * @author linsongjin
 */
@Service
@TableServiceAnnotation(value = TableServiceEnum.RECOMMEND)
public class RecommendServiceImpl extends AbstractService {

    @Resource
    private RecommendDao dao;
    @Resource
    private FullHtmlDao fullHtmlDao;

    @Override
    public String getFullHtml(Integer id) {
        return null;
    }
}

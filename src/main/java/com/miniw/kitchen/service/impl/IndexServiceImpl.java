package com.miniw.kitchen.service.impl;

import com.miniw.kitchen.annotation.TableServiceAnnotation;
import com.miniw.kitchen.dao.FullHtmlDao;
import com.miniw.kitchen.dao.IndexDao;
import com.miniw.kitchen.dao.RecommendTypeDao;
import com.miniw.kitchen.renum.TableServiceEnum;
import com.miniw.kitchen.service.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * banner Service Impl
 * @author linsongjin
 */
@Service
@TableServiceAnnotation(value = TableServiceEnum.INDEX)
public class IndexServiceImpl extends AbstractService {

    @Resource
    private IndexDao dao;
    @Resource
    private FullHtmlDao fullHtmlDao;
    @Resource
    private RecommendTypeDao recommendTypeDao;

    @Override
    public String getFullHtml(Integer id) {
        return null;
    }
}

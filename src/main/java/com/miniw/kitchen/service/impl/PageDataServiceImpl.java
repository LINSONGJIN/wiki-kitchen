package com.miniw.kitchen.service.impl;

import com.miniw.kitchen.dao.PageDataDao;
import com.miniw.kitchen.manager.PageDataRepository;
import com.miniw.kitchen.pojo.entity.PageDataDo;
import com.miniw.kitchen.service.PageDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 页面索引数据 Service
 * @author linsongjin
 */
@Service
public class PageDataServiceImpl implements PageDataService {

    @Resource
    private PageDataDao pageDataDao;

    @Resource
    private PageDataRepository repository;

    @Override
    public void insert(String tableName, Integer dataId) {

        String url = tableName + "/" + dataId + ".html";

        Map<String, PageDataDo> pageData = repository.getPageData();
        List<String> alreadyDeal = new ArrayList<>();
        pageData.forEach((k, v) -> {
            alreadyDeal.add(k);
            PageDataDo parentPageData = repository.getSideBarData(dataId);
            String nowDataParentTitle = parentPageData.getParentTitle();
            Integer typeId = parentPageData.getTypeId();

            // 数据标记
            v.setTableName(tableName);
            v.setDataId(dataId);
            v.setParentTitle(nowDataParentTitle);
            v.setUrl(url);
            v.setTypeId(typeId);
            pageDataDao.insertSingle(v);

            // 下级数据的新增
            Map<String, List<PageDataDo>> pageContent = repository.getPageContentData(k);
            repository.removePageContentData(k);
            if(pageContent != null){
                pageContent.forEach((key, dataList) -> {
                    if(dataList != null && dataList.size() > 0){
                        List<PageDataDo> insertList = new ArrayList<>();
                        for (int i = 0; i < dataList.size(); i++) {
                            PageDataDo d = dataList.get(i);
                            d.setTableName(tableName);
                            d.setDataId(dataId);
                            d.setUrl((url + d.getPoint()));
                            d.setParentTitle((v.getParentTitle() + "," + d.getParentTitle()));
                            d.setTypeId(typeId);
                            insertList.add(d);
                        }

                        pageDataDao.insertBatch(insertList);
                    }
                });
            }
        });

        // 也同步移除缓存数据
        alreadyDeal.forEach(pageData::remove);
    }

    @Override
    public void delete(String tableName, Integer dataId) {
        // 不需要考虑级联删除的情况，因为一开始就设计为不允许级联删除数据
        pageDataDao.deleteById(tableName, dataId);
    }

    @Override
    public void changeTitle(String tableName, Integer dataId, String newTitle) {
        pageDataDao.updateTitle(tableName, dataId, newTitle);
    }

    @Override
    public List<PageDataDo> selectData(String tableName, Integer dataId) {
        return pageDataDao.selectData(tableName, dataId);
    }
}

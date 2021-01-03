package com.miniw.kitchen.solr.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solr Service
 * @author linsongjin
 */
public abstract class SolrBaseService {

    private final Logger LOGGER = LoggerFactory.getLogger("solrMsg");

    @Value("${spring.data.solr.host}")
    private String baseUrl;

    protected static final String WIKI_COLLEGE = "wiki_college";

    /** 缓存 */
    protected static final Map<String, SolrClient> CLIENTS = new HashMap<>();

    private SolrClient getClient(String core){
        String url = baseUrl + "/" + core;

        if(CLIENTS.containsKey(core)){
            return CLIENTS.get(core);
        }

        synchronized (this){
            try {
                if (CLIENTS.containsKey(core)) { return CLIENTS.get(core); }

                SolrClient solrClient = new HttpSolrClient.Builder(url).build();
                if (solrClient.ping() != null){
                    CLIENTS.put(core,solrClient);
                    return solrClient;
                }

                return null;
            } catch (Exception e) {
                LOGGER.error("Get solr client error! {} ",e.getMessage());
                return null;
            }
        }
    }

    /**
     * 添加索引数据
     * @param core     指定 Solr Core
     * @param documents 新增的数据
     */
    protected void addData(String core, List<SolrInputDocument> documents) {
        SolrClient solrClient = getClient(core);
        try {
            UpdateResponse response = solrClient.add(documents);
            solrClient.commit();
        } catch (Exception e) {
            LOGGER.error("Solr add data error! {} ", e.getMessage());
        }
    }

    protected void delete(String core, SolrQuery query){
        SolrClient solrClient = getClient(core);
        try {
            UpdateResponse response = solrClient.deleteByQuery(query.getQuery());
            solrClient.commit();
            System.out.println(response.getRequestUrl());
        } catch (Exception e) {
            LOGGER.error("Solr add data error! {} ", e.getMessage());
        }
    }
}

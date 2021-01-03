package com.miniw.kitchen.dao;

import com.miniw.kitchen.pojo.dto.RecommendDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 推荐位 Dao
 * @author linsongjin
 */
public interface RecommendDao {

    RecommendDTO selectById(@Param("id") Integer id);
}

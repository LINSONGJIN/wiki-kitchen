package com.miniw.kitchen.dao;

import com.miniw.kitchen.pojo.dto.WikiDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WikiDao {

    WikiDTO selectById(@Param("id") Integer id);

    List<WikiDTO> selectData(WikiDTO wikiDTO);

    List<WikiDTO> selectAllData();
}

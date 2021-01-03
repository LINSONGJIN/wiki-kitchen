package com.miniw.kitchen.dao;

import com.miniw.kitchen.pojo.dto.FullHtmlDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 富文本 DAO
 * @author linsongjin
 */
public interface FullHtmlDao {

    FullHtmlDTO selectById(@Param("id") Integer id);
}

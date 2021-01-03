package com.miniw.kitchen.dao;

import com.miniw.kitchen.pojo.dto.IndexDTO;

public interface IndexDao {
    IndexDTO selectById(Integer id);
}

package com.miniw.kitchen.dao;

import com.miniw.kitchen.pojo.entity.PageDataDo;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * wiki 索引数据对应 Dao
 * @author linsongjin
 */
@Mapper
public interface PageDataDao {

    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "parentTitle", column = "parentTitle"),
            @Result(property = "title", column = "title"),
            @Result(property = "url", column = "url"),
            @Result(property = "point", column = "point"),
            @Result(property = "content", column = "content"),
            @Result(property = "tableName", column = "table_name"),
            @Result(property = "dataId", column = "data_id"),
            @Result(property = "typeId", column = "type_id"),
    })
    @Select("select id, parentTitle, title, url, point, content, table_name, data_id, type_id " +
            "from wiki_page_data where table_name = #{tableName} and data_id = #{dataId}")
    List<PageDataDo> selectData(@Param("tableName") String tableName, @Param("dataId") Integer dataId);

    @Transactional
    @Insert("<script>" +
            "insert into wiki_page_data (parentTitle, title, url, point, content, table_name, data_id, type_id) " +
            "values(" +
            "   #{parentTitle}, #{title}, #{url}, #{point}, #{content}, #{tableName}, #{dataId}, #{typeId}" +
            ")" +
            "</script>")
    int insertSingle(PageDataDo pageDataDo);

    @Insert("<script>" +
            "insert into wiki_page_data (parentTitle, title, url, point, content, table_name, data_id, type_id) " +
            "values " +
            "   <foreach item ='data' index='index' collection='list' separator=','>" +
            "       (#{data.parentTitle}, #{data.title}, #{data.url}, #{data.point}, #{data.content}, #{data.tableName}, #{data.dataId}, #{data.typeId})" +
            "   </foreach> " +
            "</script>")
    int insertBatch(@Param("list") List<PageDataDo> dataList);

    @Transactional(rollbackFor = Exception.class)
    @Update("<script>" +
            "   UPDATE wiki_page_data set parentTitle = #{title} " +
            "   where table_name = #{tableName} and data_id = #{id}" +
            "</script>")
    void updateTitle(@Param("tableName") String tableName, @Param("dataId") Integer dataId, @Param("title") String newParentTitle);

    @Delete("<script>" +
            "delete from wiki_page_data where table_name = #{tableName} and data_id = #{id}" +
            "</script>")
    int deleteById(@Param("tableName") String tableName, @Param("id") Integer id);

}

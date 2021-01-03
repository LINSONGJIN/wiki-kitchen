package com.miniw.kitchen.pojo.dto;

import lombok.Data;

import java.lang.reflect.Field;


/**
 * 抽象记录类
 * @author linsongjin
 */
@Data
public abstract class AbstractRecordDTO {

    /** id */
    private Integer id;
    /** 名称 */
    private String name;
    /** 说明 */
    private String content;
    /** 排序 */
    private Integer sort;
    /** 是否启用：0否1是 */
    private Integer status;

    private Long createTime;
    private String creator;
    private String creatorId;

    private Long updateTime;
    private String updater;
    private String updaterId;


    /** 更新 */
    public Object update(Object optObj) {
        Field[] fieldsArr = this.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fieldsArr) {
            try {
                Field objField = optObj.getClass().getSuperclass().getDeclaredField(field.getName());
                field.setAccessible(true);
                Object value = objField.get(optObj);
                field.set(this, value);
            } catch (NoSuchFieldException | IllegalAccessException e) { }
        }
        specialUpdate(optObj);
        return this;
    }

    /** 特别更新 */
    protected abstract void specialUpdate(Object optObj);

}

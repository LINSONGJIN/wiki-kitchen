package com.miniw.kitchen.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 轮播图 DTO
 * @author linsongjin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerDTO extends AbstractRecordDTO implements Cloneable {

    private String url;
    private String redUrl;

    /** 生效时间 */
    private Long startTime;
    private Long endTime;


    public BannerDTO(Integer id) {
        super.setId(id);
    }

    public BannerDTO(Integer id, Integer status) {
        setId(id);
        setStatus(status);
    }

    @Override
    public BannerDTO clone() {
        BannerDTO newBanner = new BannerDTO();
        newBanner.setName(super.getName());
        newBanner.setUrl(url);
        newBanner.setRedUrl(redUrl);
        return newBanner;
    }

    @Override
    protected void specialUpdate(Object obj) {
        BannerDTO optObj = (BannerDTO) obj;
        if(optObj.getUrl() != null){ this.url = optObj.getUrl(); }
        if(optObj.getRedUrl() != null){ this.redUrl = optObj.getRedUrl(); }
        if(optObj.getStartTime() != null){ this.startTime = optObj.getStartTime(); }
        if(optObj.getEndTime() != null){ this.endTime = optObj.getEndTime(); }
    }
}

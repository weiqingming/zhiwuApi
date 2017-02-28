package com.zhiwu.model;

/**
 * Created by 韦庆明 on 2016/12/8.
 * 二级分类表实体类
 */
public class TowSortModel {
    private Integer id = null;
    private Integer sortId = null;
    private String towSortName;
    private String towSortImgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getTowSortName() {
        return towSortName;
    }

    public void setTowSortName(String towSortName) {
        this.towSortName = towSortName;
    }

    public String getTowSortImgUrl() {
        return towSortImgUrl;
    }

    public void setTowSortImgUrl(String towSortImgUrl) {
        this.towSortImgUrl = towSortImgUrl;
    }
}

package com.zhiwu.model;

/**
 * Created by 韦庆明 on 2016/12/4.
 * 一级分类的实体表
 */
public class SortModel extends UsersTokenModel{

    private Integer id;//一级分类ID
    private String sortName;//一级分类名称
    private String sortImgUrl;//一级分类图标地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortImgUrl() {
        return sortImgUrl;
    }

    public void setSortImgUrl(String sortImgUrl) {
        this.sortImgUrl = sortImgUrl;
    }
}

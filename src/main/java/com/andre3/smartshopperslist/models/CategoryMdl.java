package com.andre3.smartshopperslist.models;

/**
 * Created by andre3 on 10/1/16.
 */
public class CategoryMdl {

    Integer catId;
    String name;


    public CategoryMdl(Integer catId, String name) {
        this.catId = catId;
        this.name = name;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

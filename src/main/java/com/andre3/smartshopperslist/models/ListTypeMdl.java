package com.andre3.smartshopperslist.models;

/**
 * Created by andre3 on 10/1/16.
 */
public class ListTypeMdl {

    Integer listId;
    String name;
    Integer catId;

    public ListTypeMdl(Integer listId, String name, Integer catId) {
        this.listId = listId;
        this.catId = catId;
        this.name = name;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

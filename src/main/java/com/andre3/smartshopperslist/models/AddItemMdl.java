package com.andre3.smartshopperslist.models;

/**
 * Created by andre3 on 10/1/16.
 */
public class AddItemMdl {

    Integer itemId;
    String name;
    Integer qty;
    String isle;
    Float cost;
    String size;
    Float weight;
    Integer store;
    Integer listtype;
    Integer cat;

    public AddItemMdl(Integer itemId, String name, Integer qty, String isle, Float cost, String size, Float weight, Integer store, Integer listtype, Integer cat) {
        this.itemId = itemId;
        this.name = name;
        this.qty = qty;
        this.isle = isle;
        this.cost = cost;
        this.size = size;
        this.weight = weight;
        this.store = store;
        this.listtype = listtype;
        this.cat = cat;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getListtype() {
        return listtype;
    }

    public void setListtype(Integer listtype) {
        this.listtype = listtype;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }
}

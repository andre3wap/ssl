package com.andre3.smartshopperslist.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.dao.AddItemDao;
import com.andre3.smartshopperslist.fragments.CreateList;
import com.andre3.smartshopperslist.models.AddItemMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/1/16.
 */

public class CreateItemImpl {
    Context context;
    AddItemMdl itemMdl;
    AddItemDao db;
    AddItemMdl oldItemMdl;

    private static String TABLE_NAME = "items";

    public CreateItemImpl(Context context, AddItemMdl itemMdl) {
        this.context = context;
        this.itemMdl = itemMdl;
        this.db = new AddItemDao(context);
    }

    public AddItemMdl getOldItemMdl() {
        return oldItemMdl;
    }

    public void setOldItemMdl(AddItemMdl oldItemMdl) {
        this.oldItemMdl = oldItemMdl;
    }

    public long save(){
        SQLiteDatabase sqw = db.getWritableDatabase();

        ContentValues values = new ContentValues();


            String item_name = itemMdl.getName().isEmpty() ? "NA" : itemMdl.getName();
            int item_qty = itemMdl.getQty() < 0 ? 0 : itemMdl.getQty();
            String item_isle = itemMdl.getIsle().isEmpty() ? "NA" : itemMdl.getIsle();
            float item_cost= itemMdl.getCost() <= 0.00 ? 0 : itemMdl.getCost();
            String item_size = itemMdl.getSize().isEmpty() ? "NA" : itemMdl.getSize();
            String item_weight = itemMdl.getWeight().isEmpty() ? "NA" : itemMdl.getWeight();

            values.put("name", item_name);
            values.put("qty", item_qty);
            values.put("isle", item_isle);
            values.put("cost",item_cost);
            values.put("size", item_size);
            values.put("weight", item_weight);
            values.put("store", itemMdl.getStore());
            values.put("listtype", itemMdl.getListtype());
            values.put("category", itemMdl.getCat());

        long id = sqw.insert(TABLE_NAME, null, values);
        sqw.close();

        return id;
    }
    public int update(){


        SQLiteDatabase update = db.getReadableDatabase();
        ContentValues values = new ContentValues();

            if(!itemMdl.getName().isEmpty()){
                values.put("name", itemMdl.getName());
            }if(itemMdl.getQty() > 0){
                values.put("qty", itemMdl.getQty());
            }if(!itemMdl.getIsle().isEmpty()){
                values.put("isle", itemMdl.getIsle());
            }if(itemMdl.getCost() >0 ){
                values.put("cost", itemMdl.getCost());
                System.out.println("a" + itemMdl.getCost());
            }if(!itemMdl.getSize().isEmpty()){
                values.put("size", itemMdl.getSize());
            }if(!itemMdl.getWeight().isEmpty()){
                values.put("weight", itemMdl.getWeight());
            }if(itemMdl.getStore()> 0){
                values.put("store", itemMdl.getStore());
            }if(itemMdl.getListtype()>0){
                values.put("listtype", itemMdl.getListtype());
            }if(itemMdl.getCat() > 0){
                values.put("category", itemMdl.getCat());
            }

        int count = update.update(
                TABLE_NAME,
                values,
                " _id ="+itemMdl.getItemId(),
                null);

        update.close();
        return count;

    }
    public ArrayList<AddItemMdl> readData(int listTypeId)
    {
        ArrayList<AddItemMdl> items = new ArrayList<>();

        String sQuery = "SELECT * FROM " + TABLE_NAME + " WHERE listtype ="+ listTypeId;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(sQuery, null);

        if(c.moveToFirst())
        {
            do {
                // Load with demo data, bad model class design ?
                AddItemMdl data = new AddItemMdl(0,null,0,null,(float)0.0, "", "", 0, 0,0);

                data.setItemId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                data.setCat(c.getInt(c.getColumnIndex("category")));
                data.setCost(c.getFloat(c.getColumnIndex("cost")));
                data.setIsle(c.getString(c.getColumnIndex("isle")));
                data.setQty(c.getInt(c.getColumnIndex("qty")));
                data.setSize(c.getString(c.getColumnIndex("size")));
                data.setListtype(c.getInt(c.getColumnIndex("listtype")));
                data.setWeight(c.getString(c.getColumnIndex("weight")));
                data.setStore(c.getInt(c.getColumnIndex("store")));

                items.add(data);

            }while(c.moveToNext());
        }

        db.close();
        return items;
    }

    public ArrayList<AddItemMdl> readItemData(int itemId)
    {
        ArrayList<AddItemMdl> items = new ArrayList<>();

        String sQuery = "SELECT * FROM " + TABLE_NAME + " WHERE _id ="+ itemId;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(sQuery, null);

        if(c.moveToFirst())
        {
            do {
                // Load with demo data, bad model class design ?
                AddItemMdl data = new AddItemMdl(0,null,0,null,(float)0.0, "", "", 0, 0,0);

                data.setItemId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                data.setCat(c.getInt(c.getColumnIndex("category")));
                data.setCost(c.getFloat(c.getColumnIndex("cost")));
                data.setIsle(c.getString(c.getColumnIndex("isle")));
                data.setQty(c.getInt(c.getColumnIndex("qty")));
                data.setSize(c.getString(c.getColumnIndex("size")));
                data.setListtype(c.getInt(c.getColumnIndex("listtype")));
                data.setWeight(c.getString(c.getColumnIndex("weight")));
                data.setStore(c.getInt(c.getColumnIndex("store")));

                items.add(data);

            }while(c.moveToNext());
        }

        return items;
    }

    public ArrayList<AddItemMdl> readAllData()
    {
        ArrayList<AddItemMdl> items = new ArrayList<>();

        String sQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(sQuery, null);

        if(c.moveToFirst())
        {
            do {
                // Load with demo data, bad model class design ?
                AddItemMdl data = new AddItemMdl(0,null,0,null,(float)0.0, "", "", 0, 0,0);

                data.setItemId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                data.setCat(c.getInt(c.getColumnIndex("category")));
                data.setCost(c.getFloat(c.getColumnIndex("cost")));
                data.setIsle(c.getString(c.getColumnIndex("isle")));
                data.setQty(c.getInt(c.getColumnIndex("qty")));
                data.setSize(c.getString(c.getColumnIndex("size")));
                data.setListtype(c.getInt(c.getColumnIndex("listtype")));
                data.setWeight(c.getString(c.getColumnIndex("weight")));
                data.setStore(c.getInt(c.getColumnIndex("store")));

                items.add(data);

            }while(c.moveToNext());
        }

        return items;
    }


}

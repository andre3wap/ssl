package com.andre3.smartshopperslist.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andre3.smartshopperslist.dao.AddItemDao;
import com.andre3.smartshopperslist.models.AddItemMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/1/16.
 */

public class CreateItemImpl {
    Context context;
    AddItemMdl itemMdl;
    AddItemDao db;

    private static String TABLE_NAME = "items";

    public CreateItemImpl(Context context, AddItemMdl itemMdl) {
        this.context = context;
        this.itemMdl = itemMdl;
        this.db = new AddItemDao(context);
    }

    public long save(){
        SQLiteDatabase sqw = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", itemMdl.getName());
        values.put("qty", itemMdl.getQty());
        values.put("isle", itemMdl.getIsle());
        values.put("cost", itemMdl.getCost());
        values.put("size", itemMdl.getSize());
        values.put("weight", itemMdl.getWeight());
        values.put("store", itemMdl.getStore());
        values.put("listtype", itemMdl.getListtype());
        values.put("category", itemMdl.getCat());

        long id = sqw.insert(TABLE_NAME, null, values);
        sqw.close();

        System.out.println("Item ID: " + id);
        return id;
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
                AddItemMdl data = new AddItemMdl(0,null,0,null,(float)0.0, "", (float)0.0, 0, 0,0);

                data.setItemId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                data.setCat(c.getInt(c.getColumnIndex("category")));
                data.setCost(c.getFloat(c.getColumnIndex("cost")));
                data.setIsle(c.getString(c.getColumnIndex("isle")));
                data.setQty(c.getInt(c.getColumnIndex("qty")));
                data.setSize(c.getString(c.getColumnIndex("size")));
                data.setListtype(c.getInt(c.getColumnIndex("listtype")));
                data.setWeight(c.getFloat(c.getColumnIndex("weight")));
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
                AddItemMdl data = new AddItemMdl(0,null,0,null,(float)0.0, "", (float)0.0, 0, 0,0);

                data.setItemId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                data.setCat(c.getInt(c.getColumnIndex("category")));
                data.setCost(c.getFloat(c.getColumnIndex("cost")));
                data.setIsle(c.getString(c.getColumnIndex("isle")));
                data.setQty(c.getInt(c.getColumnIndex("qty")));
                data.setSize(c.getString(c.getColumnIndex("size")));
                data.setListtype(c.getInt(c.getColumnIndex("listtype")));
                data.setWeight(c.getFloat(c.getColumnIndex("weight")));
                data.setStore(c.getInt(c.getColumnIndex("store")));

                items.add(data);

            }while(c.moveToNext());
        }

        return items;
    }


}

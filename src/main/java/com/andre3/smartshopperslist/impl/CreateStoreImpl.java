package com.andre3.smartshopperslist.impl;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.TwoStatePreference;
import android.provider.Settings;

import com.andre3.smartshopperslist.dao.CreateCategoryDao;
import com.andre3.smartshopperslist.dao.CreateStoreDao;
import com.andre3.smartshopperslist.models.StoreMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/1/16.
 */

public class CreateStoreImpl {

    Context context;
    StoreMdl storeObj;
    CreateStoreDao db;

    public static String TABLE_NAME = "stores";

    public CreateStoreImpl(Context context, StoreMdl storeObj) {
        this.context = context;
        this.db =  new CreateStoreDao(this.context);
        this.storeObj = storeObj;
    }

    public long save(){

       SQLiteDatabase dbw =  db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",storeObj.getStoreName());
        values.put("phone",storeObj.getStoreNo());
        values.put("address",storeObj.getStoreLcn());

        long rid = dbw.insert(TABLE_NAME, null, values);
        dbw.close();
        return rid;
    }

    public ArrayList<StoreMdl> readData()
    {

        ArrayList<StoreMdl> stores = new ArrayList<StoreMdl>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(selectQuery, null);

        if(c.moveToFirst())
        {
            do{
                StoreMdl model = new StoreMdl(null, null, null, 0);

                model.setStoreId(c.getInt(c.getColumnIndex("_id")));
                model.setStoreLcn(c.getString(c.getColumnIndex("address")));
                model.setStoreName(c.getString(c.getColumnIndex("name")));
                model.setStoreNo(c.getString(c.getColumnIndex("phone")));

                stores.add(model);
            }while(c.moveToNext());
        }


        return stores;
    }

    public ArrayList<StoreMdl> readDataById(int storeId)
    {

        ArrayList<StoreMdl> stores = new ArrayList<StoreMdl>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ storeId;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(selectQuery, null);

        if(c.moveToFirst())
        {
            do{
                StoreMdl model = new StoreMdl(null, null, null, 0);

                model.setStoreId(c.getInt(c.getColumnIndex("_id")));
                model.setStoreLcn(c.getString(c.getColumnIndex("address")));
                model.setStoreName(c.getString(c.getColumnIndex("name")));
                model.setStoreNo(c.getString(c.getColumnIndex("phone")));

                stores.add(model);
            }while(c.moveToNext());
        }


        return stores;
    }

    public int update() {

        SQLiteDatabase update = db.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", storeObj.getStoreName());
        values.put("address", storeObj.getStoreLcn());

        int count = update.update(
                TABLE_NAME,
                values,
                " _id ="+storeObj.getStoreId(),
                null);

        update.close();

        return count;
    }

    public void delete() {

        SQLiteDatabase delete = db.getReadableDatabase();
        delete.delete(TABLE_NAME, "WHERE _id = ?", new String[] {Integer.toString( storeObj.getStoreId())});
    }
}

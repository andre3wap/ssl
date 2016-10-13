package com.andre3.smartshopperslist.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTableLockedException;
import android.provider.Settings;

import com.andre3.smartshopperslist.dao.CreateListDao;
import com.andre3.smartshopperslist.models.ListTypeMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/1/16.
 */

public class CreateListTypeImpl {

    Context context;
    private static String TABLE_NAME = "list_type";
    ListTypeMdl listtypeObj;
    CreateListDao db;


    public CreateListTypeImpl(Context context, ListTypeMdl listtypeObj) {
        this.context = context;
        this.listtypeObj = listtypeObj;
        this.db = new CreateListDao(context);
    }

    public long save(){

        SQLiteDatabase dbw = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",listtypeObj.getName());
        values.put("cat_id",listtypeObj.getCatId());

        long id = dbw.insert(TABLE_NAME, null, values);
        dbw.close();

        System.out.println("List Type: " + listtypeObj.getCatId() + "-" + listtypeObj.getName());
        return id;
    }

    public ArrayList<ListTypeMdl> readData(int cat_id)
    {
        ArrayList<ListTypeMdl>  listtype = new ArrayList<>();
        String sQuery = "SELECT * FROM " + TABLE_NAME + " WHERE cat_id ="+ cat_id;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(sQuery, null);

        if(c.moveToFirst())
        {
            do{

                ListTypeMdl data = new ListTypeMdl(0,null, 0);
                data.setListId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                listtype.add(data);

            }while(c.moveToNext());
        }

        return listtype;
    }

    public ArrayList<ListTypeMdl> readDataAll()
    {
        ArrayList<ListTypeMdl>  listtype = new ArrayList<>();
        String sQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(sQuery, null);

        if(c.moveToFirst())
        {
            do{

                ListTypeMdl data = new ListTypeMdl(0,null,0);
                data.setListId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));
                listtype.add(data);

            }while(c.moveToNext());
        }

        return listtype;
    }


    public void update(){

    }
   public void delete() {

        SQLiteDatabase delete = db.getReadableDatabase();
         delete.delete(TABLE_NAME, "WHERE _id = ?", new String[] {Integer.toString( listtypeObj.getListId())});
         delete.delete("items", "WHERE listtype = ?", new String[] {Integer.toString( listtypeObj.getListId())});
        delete.close();
    }

}

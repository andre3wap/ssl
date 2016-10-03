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
    private static String TABLE_NAME = "listtype";
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

        long id = dbw.insert(TABLE_NAME, null, values);
        dbw.close();

        System.out.println("List Type: " + id);
        return id;
    }
    public ArrayList<ListTypeMdl> readData(int cat_id)
    {
        ArrayList<ListTypeMdl>  listtype = new ArrayList<>();
        String sQuery = "SELECT * FROM " + TABLE_NAME + "WHERE cat_id ="+ cat_id;

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

    public ArrayList<ListTypeMdl> readDataCount()
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
    public void delete(){

    }

}

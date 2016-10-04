package com.andre3.smartshopperslist.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andre3.smartshopperslist.dao.CreateCategoryDao;
import com.andre3.smartshopperslist.models.CategoryMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/1/16.
 */

public class CreateCatImpl {

    Context context;
    CategoryMdl catObj;
    CreateCategoryDao db;

    private static String TABLE_NAME = "categories";

    public CreateCatImpl(Context context, CategoryMdl catObj) {
        this.context = context;
        this.catObj = catObj;
        this.db = new CreateCategoryDao(context);
    }

    public long save(){

        SQLiteDatabase sqlw = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", catObj.getName());
        long id = sqlw.insert(TABLE_NAME, null, values);

        System.out.println("Cat ID" + id);
        return id;
    }
    public ArrayList <CategoryMdl> readData(){

        ArrayList<CategoryMdl> cats = new ArrayList<CategoryMdl>();
        String sQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase dbw = db.getReadableDatabase();
        Cursor c = dbw.rawQuery(sQuery,null);

        if(c.moveToFirst())
        {
            do{

                CategoryMdl data = new CategoryMdl(0, null);

                data.setCatId(c.getInt(c.getColumnIndex("_id")));
                data.setName(c.getString(c.getColumnIndex("name")));

                cats.add(data);
            }while(c.moveToNext());
        }

        return cats;
    }
    public void update(){

    }
    public void delete() {

    }
}

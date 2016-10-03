package com.andre3.smartshopperslist.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andre3 on 10/1/16.
 */

public class AddItemDao extends SQLiteOpenHelper {

    // DB info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SmartShoppersSSL.db";



    public AddItemDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        ///db.execSQL(SQL_ITEM_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        ////db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }



}

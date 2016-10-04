package com.andre3.smartshopperslist.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andre3 on 10/1/16.
 */
public class CreateStoreDao extends SQLiteOpenHelper {


    // DB table names
    private static String TABLE_NAME = "stores";
    private static String TABLE_NAME1 = "items";
    private static String TABLE_NAME2 = "categories";
    private static String TABLE_NAME3 = "listtype";


    // DB table columns
    private static String COLUMN_ID = "_id";
    private static String COLUMN_NAME = "name";
    private static String COLUMN_QTY = "qty";
    private static String COLUMN_ISLE = "isle";
    private static String COLUMN_COST = "cost";
    private static String COLUMN_SIZE = "size";
    private static String COLUMN_WEIGHT = "weight";
    private static String COLUMN_STORE = "store";
    private static String COLUMN_LISTTYPE = "listtype";
    private static String COLUMN_CAT = "category";
    private static String COLUMN_NUMBER = "phone";
    private static String COLUMN_LCN = "address";
    private static String COLUMN_CATID = "cat_id";

    private static final String TEXT_TYPE1 = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";



    // DB info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SmartShoppersSSL.db";



    private static final String SQL_STORE_TBL =
            "CREATE TABLE " + TABLE_NAME + "(" +
             COLUMN_ID + " INTEGER PRIMARY KEY," +
             COLUMN_NAME + " TEXT," +
             COLUMN_NUMBER + " TEXT," +
             COLUMN_LCN +" "+ TEXT_TYPE+")";


    private static final String SQL_ITEM_TBL =
            "CREATE TABLE " + TABLE_NAME1 + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_QTY + " INTEGER," +
                    COLUMN_ISLE + " TEXT," +
                    COLUMN_COST + " FLOAT," +
                    COLUMN_SIZE + " TEXT," + //SMALL/MEDIUM
                    COLUMN_WEIGHT + " FLOAT," +
                    COLUMN_STORE + " INTEGER," +
                    COLUMN_LISTTYPE + "INTEGER," +
                    COLUMN_CAT +" "+  TEXT_TYPE1+")";

    private static final String SQL_CAT_TBL =
            "CREATE TABLE " + TABLE_NAME2 + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME +" "+ TEXT_TYPE+")";

    private static final String SQL_LIST_TBL =
            "CREATE TABLE " + TABLE_NAME3 + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_CATID + " "+TEXT_TYPE1+" "+
                    COLUMN_NAME +" "+  TEXT_TYPE+")";




    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME + "," + TABLE_NAME1+ "," + TABLE_NAME2 + "," + TABLE_NAME3;

    public CreateStoreDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_STORE_TBL);
        db.execSQL(SQL_ITEM_TBL);
        db.execSQL(SQL_CAT_TBL);
        db.execSQL(SQL_LIST_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

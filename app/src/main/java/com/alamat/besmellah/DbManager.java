package com.alamat.besmellah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {


    private static final String dbname = "dbhadees";

    public DbManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "CREATE TABLE tbl_hadees ( id integer primary key autoincrement, title text, content text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry = "DROP TABLE IF EXISTS tbl_hadees";
        db.execSQL(qry);
        onCreate(db);
    }


    public String addrecord(String title, String content) {
        SQLiteDatabase dp = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("content", content);
        float res = dp.insert("tbl_hadees", null, cv);

        if (res == -1)
            return "Failed";
        else
            return "Successfully inserted";

    }

    public void emptydb(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "delete from tbl_hadees";
        db.execSQL(qry);
    }


    public Cursor readdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_hadees order by id desc";
        Cursor cursor = db.rawQuery(qry, null);
//        db.execSQL(qry);
        return cursor;
    }



//
//
//    public Cursor readtitle() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String qry = "select title from tbl_hadees order by id desc";
//        Cursor cursor = db.rawQuery(qry, null);
////        db.execSQL(qry);
//        return cursor;
//    }
//
//    public Cursor readtcontents() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String qry = "select content from tbl_hadees order by id desc";
//        Cursor cursor = db.rawQuery(qry, null);
////        db.execSQL(qry);
//        return cursor;
//    }
}

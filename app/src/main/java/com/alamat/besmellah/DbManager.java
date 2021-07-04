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
        String qry = "CREATE TABLE tbl_hadees (id integer primary key AUTOINCREMENT, title text, content text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry = "DROP TABLE IF EXISTS tbl_hadees";
        db.execSQL(qry);
        onCreate(db);
    }


    public String addrecord(int id, String title, String content) {
        SQLiteDatabase dp = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("title", title);
        cv.put("content", content);
        float res = dp.insert("tbl_hadees", null, cv);
        if (res == -1)
            return "Failed";
        else
            return "Successfully inserted";

    }

    public String emptydb() {
        SQLiteDatabase db = this.getWritableDatabase();

        /// drop it to restart the id from 1
        String qry = "DROP TABLE IF EXISTS tbl_hadees";
        db.execSQL(qry);
        onCreate(db);

        //////
//        String qry = "delete from tbl_hadees";
//        db.execSQL(qry);
        float res = db.delete("tbl_hadees", null, null);
        if (res == -1)
            return "Failed";
        else
            return "All Records Are Successfully Deleted";
    }


    public Cursor readdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_hadees order by id asc";
        Cursor cursor = db.rawQuery(qry, null);
//        db.execSQL(qry);
        return cursor;
    }

    public String deleteonerecord(int index) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String qry = "DELETE FROM tbl_hadees WHERE id='" + index + "';";
//        db.execSQL(qry);

        float res = db.delete("tbl_hadees", "id" + "=" + index, null);
        if (res == -1)
            return "Failed";
        else
            return "Record Is Successfully Deleted" + index;
    }

//    public int lastPresentID(){
//        int lastID = 0;
//        SQLiteDatabase db = this.getWritableDatabase();
//        String qry = "SELECT coalesce(ID) FROM tbl_hadees WHERE ID = (SELECT MAX(ID)  FROM tbl_hadees);";
//        lastID = (int)db.execSQL(qry);
//        db.;
//        return lastID;
//    }

    public int getLastInsertedId() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT id FROM tbl_hadees ORDER BY id DESC LIMIT 1";
        Cursor c = db.rawQuery(qry, null);
        int lastInsertedId = 0;
        while (c.moveToNext()) {
            lastInsertedId = c.getInt(0);
        }
        c.close();
        return lastInsertedId;
    }

    public void updateIDValues() {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = 0;

        String qry = "CREATE TABLE tbl_hadees_new (id integer primary key AUTOINCREMENT, title text, content text);";

        String qry1 = "INSERT INTO tbl_hadees_new(title, content)" +
                " SELECT title, content" +
                " FROM tbl_hadees ;";

        String qry2= "DROP TABLE tbl_hadees;";

        String qry3 = "ALTER TABLE tbl_hadees_new RENAME TO tbl_hadees;";

//        db.update("tbl_hadees", "id");
//        db.update( "tbl_hadees" + " SET " + "id" + " =  " + "id" + " + 1", null);
//        String qry = " UPDATE tbl_hadees SET id = "+ id + "+1";
//        String qry1 = "ALTER TABLE tbl_hadees Rename COLUMN id TO id;";
        db.execSQL(qry);
        db.execSQL(qry1);
        db.execSQL(qry2);
        db.execSQL(qry3);
//        ALTER TABLE tableName AUTO_INCREMENT = 1;
    }
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


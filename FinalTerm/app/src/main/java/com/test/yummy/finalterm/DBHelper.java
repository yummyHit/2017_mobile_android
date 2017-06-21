package com.test.yummy.finalterm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by hyeoks on 2017-06-12.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    //CREATE DATABASE
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    //CREATE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE IF NOT EXISTS TEST_TABLE (");
        sb.append(" USER_ID TEXT PRIMARY KEY, ");
        sb.append(" USER_PW TEXT NOT NULL, ");
        sb.append(" USER_NAME TEXT NOT NULL, ");
        sb.append(" PHONE TEXT, ");
        sb.append(" JOIN_DATE TEXT) ");

        db.execSQL(sb.toString());
        Toast.makeText(context, "TABLE is created.", Toast.LENGTH_SHORT).show();
    }

    //INSERT DATA
    public void insertRegistData(String id, String pw, String name, String phone, String joindate){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO TEST_TABLE VALUES( ");
        sb.append(" '" + id + "', " + pw + ", '" + name + "', " + phone + ", '" + joindate + "'); ");

        db.execSQL(sb.toString());
        Toast.makeText(context, "Insert Complete.", Toast.LENGTH_SHORT).show();
    }

    //UPDATE DATA
    public void updateRegistData(String id, String name){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();

        sb.append(" UPDATE TEST_TABLE SET USER_NAME = " + name + " WHERE USER_ID = '" + id + "';");

        db.execSQL(sb.toString());
        Toast.makeText(context, "Update Complete.", Toast.LENGTH_SHORT).show();
    }

    //DELETE DATA
    public void deleteRegistData(String id){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();

        sb.append(" DELETE FROM TEST_TABLE WHERE USER_ID = '" + id + "';");
        db.execSQL(sb.toString());
        db.close();
    }

    //SHOW DATABASE STRUCTURE
    public String showDB(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM TEST_TABLE", null);
        while(cursor.moveToNext()){
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " : "
                    + cursor.getString(2)
                    + "\n";
        }
        return result;
    }

    //IF DATABASE IS UPGRADED, THIS METHOD WILL BE CALLED.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "Version is upgraded", Toast.LENGTH_SHORT).show();
    }
}

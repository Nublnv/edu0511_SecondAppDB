package com.example.secondapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.secondapp.database.UserDbSchema.*;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SYSTEM INFO","Method onCreate(SQLiteDatabase db) called.");
        db.execSQL("CREATE TABLE " + UserTable.NAME + "("+
                "_id integer primary key autoincrement,"+
                UserTable.Cols.UUID + "," +
                UserTable.Cols.FIRSTNAME + "," +
                UserTable.Cols.LASTNAME + "," +
                UserTable.Cols.PHONE +
                ")");
        Log.d("SYSTEM INFO","Method onCreate(SQLiteDatabase db) ended.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

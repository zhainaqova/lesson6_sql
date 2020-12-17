package com.example.lesson_6_bonus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final  int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacDB";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    public DBHelper(Context context) {
        super ( context , DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ( "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID +
                             " INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_EMAIL + "TEXT"+")" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        db.execSQL ( "DROP TABLE IF EXISTS "+ TABLE_CONTACTS );

        onCreate ( db );

    }
}

package com.example.payfollow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME=".db";
    private static final int DB_VERSION=1;
    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client(idClt INTEGER PRIMARY KEY AUTOINCREMENT,nomClt TEXT NOT NULL , villeClt TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == 2){
            db.execSQL("ALTER TABLE client ADD COLUMN email TEXT");
        }
    }
}

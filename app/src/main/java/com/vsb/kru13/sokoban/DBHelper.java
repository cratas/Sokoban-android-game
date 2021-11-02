package com.vsb.kru13.sokoban;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Levels.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Levels(id TEXT primary key, image TEXT, name TEXT, highest_score TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Levels");
    }

    public Boolean insertLevelsData(String id, String image, String name, String highestScore) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("image", image);
        contentValues.put("name", name);
        contentValues.put("highest_score", highestScore);
        long result = DB.insert("Levels", null, contentValues);

        if(result==1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateLevelsData(String id, String image, String name, String highestScore) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", image);
        contentValues.put("name", name);
        contentValues.put("highest_score", highestScore);
        Cursor cursor = DB.rawQuery("Select * from Levels where id = ?", new String[]{id});
        if(cursor.getCount() > 0) {
            long result = DB.update("Levels", contentValues,"id=?", new String[]{id});

            if(result==1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Levels", null);
        return cursor;
    }
}

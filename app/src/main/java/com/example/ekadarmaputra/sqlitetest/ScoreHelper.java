package com.example.ekadarmaputra.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class ScoreHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "High";
    // tasks table name
    public static final String TABLE_HIGHSCORE = "high";
    // tasks Table Columns names
    public static final String KEY_ID = "qid";
    public static final String KEY_NAME = "username";
    public static final String KEY_SCORE = "score"; // correct option

    public ScoreHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_HIGHSCORE + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
                + " TEXT, " + KEY_SCORE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE );
// Create tables again
        onCreate(db);
    }

    public boolean insert (String name, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(KEY_NAME, name);
        content.put(KEY_SCORE, score);
        long result = db.insert(TABLE_HIGHSCORE, null, content);

        if (result == -1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_HIGHSCORE;
        Cursor cursor = db.rawQuery(selectQuery , null);
        return cursor;
    }
}

package com.example.ekadarmaputra.sqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mathsone";
    // tasks table name
    public static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    public static final String KEY_ID = "qid";
    public static final String KEY_QUES = "question";
    public static final String KEY_ANSWER = "answer"; // correct option
    public static final String KEY_OPTA = "opta"; // option a
    public static final String KEY_OPTB = "optb"; // option b
    public static final String KEY_OPTC = "optc"; // option c
    public SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
// Create tables again
        onCreate(db);
    }


    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUEST + " ORDER BY RANDOM() LIMIT 5";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }
}

package com.example.ekadarmaputra.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class ScoreActivity extends AppCompatActivity {
    ScoreHelper db;
    SQLiteDatabase myDb;
    Cursor cursor;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_score);

        listView = (ListView) findViewById(R.id.List);
        db = new ScoreHelper(this);
        ArrayList<String> List = new ArrayList<>();
        cursor = db.getAllData();

        if(cursor.getCount()== 0){
            Toast.makeText(ScoreActivity.this, "No data to display", Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                List.add(cursor.getString(1));
                List.add(cursor.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List);
                listView.setAdapter(listAdapter);
            }
        }
    }
}

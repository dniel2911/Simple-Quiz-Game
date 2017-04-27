package com.example.ekadarmaputra.sqlitetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class DisplayActivity extends Activity {
    public DbHelper mHelper;
    public SQLiteDatabase dataBase;

    public ArrayList<String> userId = new ArrayList<String>();
    public ArrayList<String> question = new ArrayList<String>();
    public ArrayList<String> answer = new ArrayList<String>();
    public ArrayList<String> optA = new ArrayList<String>();
    public ArrayList<String> optB = new ArrayList<String>();
    public ArrayList<String> optC = new ArrayList<String>();

    public ListView userList;
    public AlertDialog.Builder build;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        userList = (ListView) findViewById(R.id.List);

        mHelper = new DbHelper(this);

        //add new record
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("update", false);
                startActivity(i);

            }
        });

        //click to update data
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("Question", question.get(arg2));
                i.putExtra("Answer", answer.get(arg2));
                i.putExtra("Option A", optA.get(arg2));
                i.putExtra("Option B", optB.get(arg2));
                i.putExtra("Option C", optC.get(arg2));
                i.putExtra("ID", userId.get(arg2));
                i.putExtra("update", true);
                startActivity(i);

            }
        });

        //long click to delete data
        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {

                build = new AlertDialog.Builder(DisplayActivity.this);
                build.setTitle("Delete " + question.get(arg2));
                build.setMessage("Do you want to delete ?");
                build.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText( getApplicationContext(),
                                question.get(arg2) , Toast.LENGTH_LONG).show();

                        dataBase.delete(
                                DbHelper.TABLE_QUEST,
                                DbHelper.KEY_ID + "="
                                        + userId.get(arg2), null);
                        displayData();
                        dialog.cancel();
                    }
                });

                build.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }

    /**
     * displays data from SQLite
     */
    private void displayData() {
        dataBase = mHelper.getWritableDatabase();
        Cursor mCursor = dataBase.rawQuery("SELECT * FROM " + DbHelper.TABLE_QUEST, null);

        userId.clear();
        question.clear();
        answer.clear();
        optA.clear();
        optB.clear();
        optC.clear();
        if (mCursor.moveToFirst()) {
            do {
                userId.add(mCursor.getString(mCursor.getColumnIndex(mHelper.KEY_ID)));
                question.add(mCursor.getString(mCursor.getColumnIndex(mHelper.KEY_QUES)));
                answer.add(mCursor.getString(mCursor.getColumnIndex(mHelper.KEY_ANSWER)));
                optA.add(mCursor.getString(mCursor.getColumnIndex(mHelper.KEY_OPTA)));
                optB.add(mCursor.getString(mCursor.getColumnIndex(mHelper.KEY_OPTB)));
                optC.add(mCursor.getString(mCursor.getColumnIndex(mHelper.KEY_OPTC)));

            } while (mCursor.moveToNext());
        }
        DisplayAdapter disadpt = new DisplayAdapter(DisplayActivity.this,userId, question, answer, optA, optB, optC);
        userList.setAdapter(disadpt);
        mCursor.close();
    }

}

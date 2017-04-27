package com.example.ekadarmaputra.sqlitetest;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_save;
    EditText editQuestion,editAnswer,editOptA, editOptB, editOptC ;
    public DbHelper mHelper;
    public SQLiteDatabase dataBase;
    public String id,question,answer, optA, optB, optC;
    public boolean isUpdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save=(Button)findViewById(R.id.save_btn);
        editQuestion=(EditText)findViewById(R.id.editQuestion);
        editAnswer=(EditText)findViewById(R.id.editAnswer);
        editOptA=(EditText)findViewById(R.id.editOptA);
        editOptB=(EditText)findViewById(R.id.editOptB);
        editOptC=(EditText)findViewById(R.id.editOptC);

        isUpdate=getIntent().getExtras().getBoolean("update");
        if(isUpdate)
        {
            id=getIntent().getExtras().getString("ID");
            question=getIntent().getExtras().getString("Question");
            answer=getIntent().getExtras().getString("Answer");
            optA=getIntent().getExtras().getString("Option A");
            optB=getIntent().getExtras().getString("Option B");
            optC=getIntent().getExtras().getString("Option C");
            editQuestion.setText(question);
            editAnswer.setText(answer);
            editOptA.setText(optA);
            editOptB.setText(optB);
            editOptC.setText(optC);
        }

        btn_save.setOnClickListener(this);

        mHelper=new DbHelper(this);

    }

    // saveButton click event
    public void onClick(View v) {
        question=editQuestion.getText().toString().trim();
        answer=editAnswer.getText().toString().trim();
        optA=editOptA.getText().toString().trim();
        optB=editOptB.getText().toString().trim();
        optC=editOptC.getText().toString().trim();

        if(question.length()>0 && answer.length()>0 && optA.length()>0 && optB.length()>0 && optC.length()>0 )
        {
            saveData();
        }
        else
        {
            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(MainActivity.this);
            alertBuilder.setTitle("Invalid Data");
            alertBuilder.setMessage("Please, Enter valid data");
            alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            alertBuilder.create().show();
        }

    }

    /**
     * save data into SQLite
     */
    private void saveData(){
        dataBase=mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DbHelper.KEY_QUES,question);
        values.put(DbHelper.KEY_ANSWER,answer);
        values.put(DbHelper.KEY_OPTA,optA);
        values.put(DbHelper.KEY_OPTB,optB);
        values.put(DbHelper.KEY_OPTC,optC);


        System.out.println("");
        if(isUpdate)
        {
            //update database with new data
            dataBase.update(DbHelper.TABLE_QUEST, values, DbHelper.KEY_ID+"="+id, null);
        }
        else
        {
            //insert data into database
            dataBase.insert(DbHelper.TABLE_QUEST, null, values);
        }
        //close database
        dataBase.close();
        finish();


    }
}

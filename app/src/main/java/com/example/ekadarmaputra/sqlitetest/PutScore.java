package com.example.ekadarmaputra.sqlitetest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class PutScore extends AppCompatActivity {
    Button btnSave, btnView;
    ScoreHelper db;
    EditText editName, editScore;
    SQLiteDatabase myDb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.putscore);

        btnSave = (Button) findViewById(R.id.saveScore);
        btnView = (Button) findViewById(R.id.viewScore);
        editName = (EditText) findViewById(R.id.editName);
        editScore = (EditText) findViewById(R.id.editScore);

        db = new ScoreHelper(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editName.getText().toString();
                String newScore = editScore.getText().toString();

                if (newName.length() != 0 && newScore.length() != 0){
                    addData(newName, newScore);
                }else{
                    Toast.makeText(PutScore.this, "please fill all the field", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PutScore.this, ScoreActivity.class);
                startActivity(intent);
            }
        });


    }

    //Add new entry data for question to database
    public void addData(String name, String score){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsert = db.insert(editName.getText().toString(), editScore.getText().toString());

                if(isInsert = true){
                    Toast.makeText(PutScore.this, "Data inserted", Toast.LENGTH_LONG).show();
                }else{
                     Toast.makeText(PutScore.this, "Failed to insert data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

package com.example.ekadarmaputra.sqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class Options extends AppCompatActivity {
    Button btnAbout,btnScore,btnStart,btnEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionsmenu);

        btnEntry = (Button) findViewById(R.id.btnAdd);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnScore = (Button) findViewById(R.id.btnScore);
        btnAbout = (Button) findViewById(R.id.btnAbout);


        //Activity to add new question
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this,
                        DisplayActivity.class);
                startActivity(intent);
            }
        });

        //Activity to play the game
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this,
                        QuestionActivity.class);
                startActivity(intent);
            }
        });

        //Activity to view current high scores in quiz game
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this,
                        ScoreActivity.class);
                startActivity(intent);
            }
        });

        //About the programmer
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this,
                        AboutMe.class);
                startActivity(intent);
            }
        });

    }
}

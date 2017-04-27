package com.example.ekadarmaputra.sqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class ResultActivity extends AppCompatActivity  {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textResult = (TextView) findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        textResult.setText("Your score is " + " " + score + ". Thanks for playing my game.");
    }

    //Intent to add score activity
    public void saveScore(View v){
        Intent intent = new Intent(this, PutScore.class);
        startActivity(intent);
    }

    //Intent to play game activity
    public void playagain(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}

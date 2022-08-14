package com.example.firstquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighScore;

    private int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewHighScore = findViewById(R.id.textView4);
        loadHighScore();

        Button buttonAdsFree = findViewById(R.id.button_adsfree_button);
        buttonAdsFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsFree();
            }
        });

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQuiz();

            }
        });


    }

    private void startQuiz() {

        Intent intent = new Intent(MainActivity.this, QuizAcitvity.class);

        startActivityForResult(intent, REQUEST_CODE_QUIZ);

    }

    private void adsFree(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.aneas.paidfirstquizverbal"));

        startActivity(intent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == REQUEST_CODE_QUIZ) {


            if(resultCode == RESULT_OK) {

                int score = data.getIntExtra(QuizAcitvity.EXTRA_SCORE, 0);

                if(score > highscore) {

                    updateHighScore(score);

                }

            }


        }


    }


    private void loadHighScore() {

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("HighScore: " + highscore);


    }


    private void updateHighScore(int highscoreNew) {

        highscore = highscoreNew;
        textViewHighScore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor  = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();


    }


}

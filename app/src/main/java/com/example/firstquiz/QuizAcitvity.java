package com.example.firstquiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class QuizAcitvity extends AppCompatActivity  {


    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLS = 60000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    //private MaxInterstitialAd interstitialAd;
    private int retryAttempt;

    String stringExample = "Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        /*AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        });*/
        setContentView(R.layout.activity_quiz_acitvity);



        /*interstitialAd = new MaxInterstitialAd( "50fcc8336b01ead0", this );
        interstitialAd.setListener((MaxAdListener) this);
        interstitialAd.loadAd();*/

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);

        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        if (savedInstanceState == null) {

            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();

        } else {

            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {

                startCountDown();

            } else {

                updateCountDownText();
                showSolution();

            }

        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showInterstitial();
                if (!answered) {

                    if (rb1.isChecked()  || rb2.isChecked() || rb3.isChecked()) {

                        checkAnswer();

                    } else {


                        Toast.makeText(QuizAcitvity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    showNextQuestion();


                }

            }
        });


    }


    private void showNextQuestion() {


        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);

        rbGroup.clearCheck();


        if (questionCounter < questionCountTotal) {

            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());



            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLS;
            startCountDown();

        } else {

            Toast.makeText(this, "Buy the full version for 30 questions and without any ads", Toast.LENGTH_LONG).show();
            finishQuiz();

        }

    }


    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();


            }

            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();

            }
        }.start();


    }


    private void updateCountDownText() {


        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;


        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);


        textViewCountDown.setText(timeFormatted);


        if (timeLeftInMillis < 10000) {

            textViewCountDown.setTextColor(Color.RED);

        } else {

            textViewCountDown.setTextColor(textColorDefaultCd);

        }

    }


    private void checkAnswer() {

        answered = true;

        countDownTimer.cancel();


        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {

            score++;
            textViewScore.setText("Score: " + score);

        }

        showSolution();

    }

    private void showSolution() {


        QuizDbHelper dbHelper = new QuizDbHelper(this);

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);



        switch(currentQuestion.getAnswerId()){
            case 1:
            textViewQuestion.setText("Cannot Say - The fourth sentence states that the policy increases China’s per capita gross" +
                    "domestic product, however this is just one economic indicator and is based on output per" +
                    "person. The passage does not tell us if overall, the country as a whole has increased wealth" +
                    "due to the one child policy. Since the passage does not tell us either way, we must respond" +
                    " Cannot Say.");
            break;

            case 2:
                textViewQuestion.setText("False - The last sentence merely presents the fact that millions of sibling-free couples are" +
                        "able to have two children, and does not speculate as to the implications.");
                break;

            case 3:
                textViewQuestion.setText("Cannot Say - The second sentence states that both the policy’s manner of enforcement and" +
                        "its social repercussions are criticised – but does not state which is the main criticism. So" +
                        " based on the information we are given, we cannot say.");
                break;


            case 4:
                textViewQuestion.setText("Cannot Say - While the 7th sentence states that the policy is enforced less strictly in" +
                        "provincial regions, comparative figures are not provided. One might deduce this statement is" +
                        "likely given what we are told, but we are not told explicitly if this is true or" +
                        "false, therefore we cannot say.");
                break;

            case 5:
                textViewQuestion.setText("True - The fifth sentence tells us that “a disparate ratio of males to females” is the result of “a" +
                        "cultural preference for sons”. Whilst it might be impossible to make assumptions about what" +
                        "each parent’s preference is, the key word in the statement in “general” which means we can" +
                        " look at the overall trend, in this case towards sons.");
                break;


            case 6:
                textViewQuestion.setText("False - The fifth sentence states that there are “limitations” and cites two “amongst others”.\n" +
                        "So we are told there are more than two limitations.");
                break;


        }

        switch (currentQuestion.getAnswerNr()) {


            case 1:
                rb1.setTextColor(Color.GREEN);
                    //textViewQuestion.setText("Correct Answer is /n I will do all I can to help you with this issue. If you give me your account details, I will investigate what has happened here.");
                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                //textViewQuestion.setText("fsefsef");
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                //textViewQuestion.setText("cccc");
                break;





        }


        if (questionCounter < questionCountTotal) {

            buttonConfirmNext.setText("Next");

        } else {

            buttonConfirmNext.setText("Finish");

        }

    }


    private void finishQuiz() {

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();

    }


    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            finishQuiz();

        } else {

            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }


        backPressedTime = System.currentTimeMillis();


    }

    /*public void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if ( interstitialAd.isReady() )
        {
            interstitialAd.showAd();
        }
    }*/




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {

            countDownTimer.cancel();

        }
    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);

    }

    /*@Override
    public void onAdLoaded(MaxAd ad) {
        retryAttempt = 0;
    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {
        interstitialAd.loadAd();
    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

        new Handler().postDelayed( new Runnable()
        {
            @Override
            public void run()
            {
                interstitialAd.loadAd();
            }
        }, delayMillis );
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
        interstitialAd.loadAd();
    }*/
}

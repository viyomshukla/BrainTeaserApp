package com.example.brainteaserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView score;
    TextView ques;
    TextView timer;
    TextView opA;
    TextView opB;
    TextView opC;
    TextView opD;
    TextView correct;
    ArrayList<Integer> list = new ArrayList<>();
    int correctidx;
    int maxscrore;
    int yourscore;

    MediaPlayer mediaPlayer;
    Random random = new Random();
    CountDownTimer countDownTimer;
    int adde=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        score = findViewById(R.id.score);
        ques = findViewById(R.id.ques);
        timer = findViewById(R.id.timer);
        opA = findViewById(R.id.opA);
        opB = findViewById(R.id.opB);
        opC = findViewById(R.id.opC);
        opD = findViewById(R.id.opD);
        correct = findViewById(R.id.correct);
        maxscrore=0;
        yourscore=0;
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.audio2);
        generateques();
        Timer();


btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        btn.setVisibility(View.INVISIBLE);
        maxscrore=0;
        yourscore=0;

        generateques();
        score.setText(yourscore+"/"+maxscrore);
        opA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option(v);
            }
        });
        opB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option(v);
            }
        });
        opC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option(v);
            }
        });
        opD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option(v);
            }
        });

        Timer();
    }
});



    }
    public void  Timer(){

        countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
                Log.i("time", String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {


                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.demo);
                mediaPlayer.start();

                btn.setVisibility(View.VISIBLE);
//                MediaPlayer.create(getApplicationContext(),R.raw.demo).start();
                opA.setOnClickListener(null);
                opB.setOnClickListener(null);
                opC.setOnClickListener(null);
                opD.setOnClickListener(null);

            }
        }.start();
    }


    public void generateques() {


            int num1 = random.nextInt(31);
            int num2 = random.nextInt(31);
            int ans;
            if(adde==1) { ans = num1 + num2;}
            else{
                ans=num1*num2;
            }


       if(adde==1) ques.setText(num1 + "+" + num2);
       else ques.setText(num1 + "*" + num2);

            correctidx = random.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == correctidx) {
                   if(adde==1){
                       list.add(num1 + num2);
                       adde=0;
                   }
                   else {
                       list.add(num1*num2);
                       adde=1;
                   }
                } else {
                    int a = random.nextInt(401);
                    while (a == ans) { // Ensure the wrong answer is not equal to the correct answer
                        a = random.nextInt(401);
                    }
                    list.add(a);

                }
        }


        opA.setText(String.valueOf(list.get(0)));
        opB.setText(String.valueOf(list.get(1)));
        opC.setText(String.valueOf(list.get(2)));
        opD.setText(String.valueOf(list.get(3)));
        list.clear();

    }


    public void option (View view){
        if (view.getTag().toString().equals(String.valueOf(correctidx))) {
            correct.setVisibility(View.VISIBLE);
            countDownTimer.cancel();
            Timer();
            ObjectAnimator fadein = ObjectAnimator.ofFloat(correct, "alpha", 0f, 1f);
            fadein.setDuration(1000); // Duration of 2 seconds
            fadein.start();
            ObjectAnimator fadeout = ObjectAnimator.ofFloat(correct, "alpha", 1f, 0f);
            fadeout.setDuration(1000); // Duration of 2 seconds
            fadeout.start();

            correct.setText("Correct!!!");
            yourscore++;
            maxscrore++;
            score.setText(yourscore+"/"+maxscrore);

            generateques();
        } else {
            correct.setVisibility(View.VISIBLE);
            ObjectAnimator fadein1 = ObjectAnimator.ofFloat(correct, "alpha", 0f, 1f);
            fadein1.setDuration(1000); // Duration of 2 seconds
            fadein1.start();
            ObjectAnimator fadeout2 = ObjectAnimator.ofFloat(correct, "alpha", 1f, 0f);
            fadeout2.setDuration(1000); // Duration of 2 seconds
            fadeout2.start();
            correct.setText("InCorrect!!!");
            countDownTimer.cancel();
            generateques();
            Timer();
            maxscrore++;
            score.setText(yourscore+"/"+maxscrore);

        }
    }

}
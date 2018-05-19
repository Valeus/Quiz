package com.example.a10019356.quizproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements QuestionFragment.scoreKeeping{

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Button start, next, exit;
    TextView welcome;
    int quest = 0;
    int score=0;
    int qt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.id_welcome);
        start = findViewById(R.id.id_start);
        next = findViewById(R.id.id_next);
        exit = findViewById(R.id.id_exit);

        qt = (int)(Math.random()*2)+1;

        while(quest<10) {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    welcome.setVisibility(view.INVISIBLE);
                    next.setVisibility(view.VISIBLE);
                    start.setVisibility(view.INVISIBLE);


                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    if(qt==1) {
                        QuestionFragment questionFragment = new QuestionFragment();
                        fragmentTransaction.add(R.id.id_question, questionFragment);
                    }else {
                        questionTrueAndFalse questionTrueAndFalse = new questionTrueAndFalse();
                        fragmentTransaction.add(R.id.id_question, questionTrueAndFalse);
                    }

                    fragmentTransaction.commit();

                    quest++;

                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentTransaction = fragmentManager.beginTransaction();
                    QuestionFragment questionFragment = new QuestionFragment();
                    fragmentTransaction.replace(R.id.id_question, questionFragment);
                    fragmentTransaction.commit();

                    quest++;
                }
            });
        }


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

    }

    @Override
    public void score(boolean cor) {
        if(cor)
            score++;

    }
}

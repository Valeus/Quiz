package com.example.a10019356.quizproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Button start, exit;
    TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.id_welcome);
        start = findViewById(R.id.id_start);
        exit = findViewById(R.id.id_exit);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                welcome.setVisibility(view.INVISIBLE);


                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                final QuestionFragment question = new QuestionFragment();
                fragmentTransaction.add(R.id.id_question,question);

                fragmentTransaction.commit();
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });



    }
}

package com.example.a10019356.quizproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends FragmentActivity{

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Button start, next, exit;
    TextView welcome;
    ArrayList<MultipleChoice> mcqs = new ArrayList<>();
    ArrayList<TrueAndFalse> tfqs = new ArrayList<>();
    int kid;
    int tfcount;
    int quest = 9;
    int score=0;
    int qt;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.id_welcome);
        start = findViewById(R.id.id_start);
        next = findViewById(R.id.id_next);
        exit = findViewById(R.id.id_exit);

        qt = (int)(Math.random()*2)+1;

        if (savedInstanceState != null) {
            kid=savedInstanceState.getInt("kid");
            tfcount=savedInstanceState.getInt("tfcount");
            quest=savedInstanceState.getInt("quest");
            score=savedInstanceState.getInt("score");
            mcqs=savedInstanceState.getParcelableArrayList("mcqs");
            tfqs=savedInstanceState.getParcelableArrayList("tfqs");
        }

        if(quest<10) {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    welcome.setVisibility(INVISIBLE);
                    next.setVisibility(VISIBLE);
                    start.setVisibility(INVISIBLE);
                    next.setClickable(true);
                    start.setClickable(false);


                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    makeMCQuestion();

                    kid = rando(0, mcqs.size() - 1);


                    makeTFQuestion();

                    tfcount = rando(0, tfqs.size() - 1);


                    if (qt == 1) {
                        QuestionFragment questionFragment = new QuestionFragment();
                        fragmentTransaction.add(R.id.id_question, questionFragment);
                        fragmentTransaction.commit();
                        quest++;
                    } else {
                        questionTrueAndFalse questionTrueAndFalse = new questionTrueAndFalse();
                        fragmentTransaction.add(R.id.id_question, questionTrueAndFalse);
                        fragmentTransaction.commit();
                        quest++;
                    }


                }
            });
        }else if(quest==10){

            next.setVisibility(INVISIBLE);
            start.setVisibility(VISIBLE);
            start.setText("Restart");

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    quest=0;

                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.id_question)).commit();

                    EndScreen endScreen = new EndScreen();
                    fragmentTransaction.replace(R.id.id_question, endScreen);

                    for(int i = mcqs.size()-1; i>0; i--){
                        mcqs.remove(i);
                    }
                    for(int i = tfqs.size()-1; i>0; i--){
                        tfqs.remove(i);
                    }

                    score=0;

                }
            });
        }


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.id_question)).commit();

                    if(quest==10){
                        EndScreen endScreen = new EndScreen();
                        fragmentTransaction.replace(R.id.id_question, endScreen);
                    }

                    qt = (int) (Math.random() * 2) + 1;

                    mcqs.remove(kid);
                    tfqs.remove(tfcount);

                    kid = rando(0, mcqs.size() - 1);
                    tfcount = rando(0, tfqs.size() - 1);


                    if (qt == 1) {
                        QuestionFragment questionFragment = new QuestionFragment();
                        fragmentTransaction.replace(R.id.id_question, questionFragment);
                        quest++;
                    } else {
                        questionTrueAndFalse questionTrueAndFalse = new questionTrueAndFalse();
                        fragmentTransaction.replace(R.id.id_question, questionTrueAndFalse);
                        quest++;
                    }

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

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("kid", kid);
        outState.putInt("tfcount", tfcount);
        outState.putInt("quest", quest);
        outState.putInt("score", score);
        outState.putParcelableArrayList("mcqs", mcqs);
        outState.putParcelableArrayList("tfqs", tfqs);
        super.onSaveInstanceState(outState);
    }

    public int getKid(){
        return kid;
    }

    public int getTfcount(){
        return tfcount;
    }

    public int getScore(){
        return score;
    }

    public int getQuest(){
        return quest;
    }

    public ArrayList getMCArrayList(){
        return mcqs;
    }

    public ArrayList getTFArrayList(){
        return tfqs;
    }

    public int rando(int start, int end){
        return (int)(Math.random()*((end-start)+1))+start;
    }

    public void makeMCQuestion(){

        mcqs.add(new MultipleChoice("Who won WWII?","Germany","Africa","Antarctica","Allied Powers","Allied Powers"));
        mcqs.add(new MultipleChoice("What is the cube root of 125?","7","5","2.5","3","5"));
        mcqs.add(new MultipleChoice("What is the largest organ in the human body?","Heart","Stomach","Skin","Eyes","Skin"));
        mcqs.add(new MultipleChoice("What is the end of a shoelace called?","An aglet","A lace-end","A knot","The tip","An aglet"));
        mcqs.add(new MultipleChoice("What is Chicago?","Continent","City","State","City on a hill","City"));
        mcqs.add(new MultipleChoice("How many notes are in an octave?","7","12","8","9","8"));
        mcqs.add(new MultipleChoice("What is the chemical formula for water?","WAT","C4W","C6H12O6","H2O","H2O"));
        mcqs.add(new MultipleChoice("What line divides North and South Korea?","Berlin Wall","The Great Wall","The Mexican Border Wall","The 38th Parallel","The 38th Parallel"));
        mcqs.add(new MultipleChoice("What is a derivative?","Slope of a tangent line","Factored form of an equation","When you solve a problem","When you derive something","Slope of a tangent line"));
        mcqs.add(new MultipleChoice("How long can someone survive without water?","3 days","3 years","3 weeks","3 centuries","3 days"));

    }

    public void makeTFQuestion(){

        tfqs.add(new TrueAndFalse("True or False: I make this program on Android Studio. ","True","False","True"));
        tfqs.add(new TrueAndFalse("True or False: 40 degrees F 40 degrees C. ","True","False","True"));
        tfqs.add(new TrueAndFalse("True or False: Impeachment mean immediate removal of office. ","True","False","False"));
        tfqs.add(new TrueAndFalse("True or False: The 13 stripes on the american flag represent the 13 founding fathers. ","True","False","False"));
        tfqs.add(new TrueAndFalse("True or False: No countries have the color purple on their flag.  ","True","False","True"));
        tfqs.add(new TrueAndFalse("True or False: The Great Leap Forward proposed by Chinese leader Mao Zedong benefited China overall. ","True","False","False"));
        tfqs.add(new TrueAndFalse("True or False: An X-Box controller has 4 main buttons on it: A, B, C and D. ","True","False","False"));
        tfqs.add(new TrueAndFalse("True or False: A jellyfish is 95% water. ","True","False","True"));
        tfqs.add(new TrueAndFalse("True or False: The creator of Spongebob used to teach marine biology. ","True","False","True"));
        tfqs.add(new TrueAndFalse("True or False: A duck's quack doesn't echo. ","True","False","False"));

    }



}

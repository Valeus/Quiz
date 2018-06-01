package com.example.a10019356.quizproject;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends FragmentActivity implements questionTrueAndFalse.ReceiveTFRadioGroup,
        QuestionFragment.ReceiveMVRadioGroup{

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Button start, next, exit, reset;
    TextView welcome;
    ArrayList<MultipleChoice> mcqs = new ArrayList<>();
    ArrayList<TrueAndFalse> tfqs = new ArrayList<>();
    int kid=0;
    int tfcount=0;
    int quest = 0;
    int score=0;
    int qt;

    RadioGroup tfOptions;
    RadioGroup mcAnswers;
    RadioGroup options;
    RadioGroup answers;
    RadioButton trueOption;
    RadioButton falseOption;
    RadioButton tru;
    RadioButton fals;
    RadioButton a;
    RadioButton b;
    RadioButton c;
    RadioButton d;
    RadioButton aa;
    RadioButton bb;
    RadioButton cc;
    RadioButton dd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.id_welcome);
        start = findViewById(R.id.id_start);
        next = findViewById(R.id.id_next);
        exit = findViewById(R.id.id_exit);
        reset = findViewById(R.id.id_reset);
        options = getTfOptions();
        tru = getTrueOption();
        fals = getFalseOption();
        answers = getMcAnswers();
        aa = getA();
        bb = getB();
        cc = getC();
        dd = getD();

        qt = (int)(Math.random()*2)+1;

        if (savedInstanceState != null) {
            kid=savedInstanceState.getInt("kid");
            tfcount=savedInstanceState.getInt("tfcount");
            quest=savedInstanceState.getInt("quest");
            score=savedInstanceState.getInt("score");
            mcqs=savedInstanceState.getParcelableArrayList("mcqs");
            tfqs=savedInstanceState.getParcelableArrayList("tfqs");
        }

        reset.setClickable(false);
        reset.setVisibility(INVISIBLE);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                welcome.setVisibility(INVISIBLE);
                next.setVisibility(VISIBLE);
                start.setVisibility(INVISIBLE);
                next.setClickable(true);
                start.setClickable(false);

                start.setText("Start");


                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                makeMCQuestion();
                makeTFQuestion();

                if (qt == 1) {
                    QuestionFragment questionFragment = new QuestionFragment();
                    fragmentTransaction.add(R.id.id_question, questionFragment);
                    fragmentTransaction.commit();
                    kid++;
                    quest++;
                } else {
                    questionTrueAndFalse questionTrueAndFalse = new questionTrueAndFalse();
                    fragmentTransaction.add(R.id.id_question, questionTrueAndFalse);
                    fragmentTransaction.commit();
                    tfcount++;
                    quest++;
                }
                Log.d("questions",""+quest);


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.id_question)).commit();

                qt = (int) (Math.random() * 2) + 1;


                if (qt == 1) {
                    QuestionFragment questionFragment = new QuestionFragment();
                    fragmentTransaction.replace(R.id.id_question, questionFragment);
                    kid++;
                    quest++;
                } else {
                    questionTrueAndFalse questionTrueAndFalse = new questionTrueAndFalse();
                    fragmentTransaction.replace(R.id.id_question, questionTrueAndFalse);
                    tfcount++;
                    quest++;
                }

                if(quest>=9) {

                    next.setClickable(false);
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.id_question)).commit();

                    EndScreen endScreen = new EndScreen();
                    fragmentTransaction.replace(R.id.id_question, endScreen);


                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            fragmentManager = getSupportFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();

                            EndScreen endScreen = new EndScreen();
                            fragmentTransaction.replace(R.id.id_question, endScreen);

                            next.setVisibility(INVISIBLE);
                            reset.setClickable(true);
                            reset.setVisibility(VISIBLE);


                        }
                    });
                }

                Log.d("questions",""+quest);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quest = 0;

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.id_question)).commit();

                EndScreen endScreen = new EndScreen();
                fragmentTransaction.replace(R.id.id_question, endScreen);

                for (int i = mcqs.size() - 1; i > 0; i--) {
                    mcqs.remove(i);
                }
                for (int i = tfqs.size() - 1; i > 0; i--) {
                    tfqs.remove(i);
                }

                kid=0;
                tfcount=0;
                score = 0;

                next.setVisibility(VISIBLE);
                start.setVisibility(INVISIBLE);
                next.setClickable(true);
                start.setClickable(false);
                reset.setClickable(false);
                reset.setVisibility(INVISIBLE);

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                makeMCQuestion();
                makeTFQuestion();

                if (qt == 1) {
                    QuestionFragment questionFragment = new QuestionFragment();
                    fragmentTransaction.replace(R.id.id_question, questionFragment);
                    kid++;
                    quest++;
                } else {
                    questionTrueAndFalse questionTrueAndFalse = new questionTrueAndFalse();
                    fragmentTransaction.replace(R.id.id_question, questionTrueAndFalse);
                    tfcount++;
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

        try{
            options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (tru.isChecked()) {
                        if (tru.getText().equals(tfqs.get(tfcount).getS())) {
                            score++;
                        }
                    }
                    if (fals.isChecked()) {
                        if (fals.getText().equals(tfqs.get(tfcount).getS())) {
                            score++;
                        }
                    }

                }
            });

            answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (aa.isChecked()) {
                        if (aa.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }
                    if (bb.isChecked()) {
                        if (bb.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }
                    if (cc.isChecked()) {
                        if (cc.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }
                    if (dd.isChecked()) {
                        if (dd.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }


                }
            });
        }catch (NullPointerException e){
            options = getTfOptions();
            tru = getTrueOption();
            fals = getFalseOption();
            answers = getMcAnswers();
            aa = getA();
            bb = getB();
            cc = getC();
            dd = getD();
           Log.d("ERROR", ""+e);
        }

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

    public RadioGroup getTfOptions() {
        return tfOptions;
    }

    public RadioButton getTrueOption(){
        return trueOption;
    }

    public RadioButton getFalseOption(){
        return falseOption;
    }

    public RadioGroup getMcAnswers(){
        return mcAnswers;
    }

    public RadioButton getA(){
        return a;
    }

    public RadioButton getB(){
        return b;
    }

    public RadioButton getC(){
        return c;
    }

    public RadioButton getD(){
        return d;
    }

    @Override
    public void receiveQTF(RadioGroup radioGroup) {
        tfOptions = radioGroup;
    }

    @Override
    public void receiveT(RadioButton radioButton) {
        trueOption = radioButton;
    }

    @Override
    public void receiveF(RadioButton radioButton) {
        falseOption = radioButton;
    }

    @Override
    public void receiveMCQ(RadioGroup radioGroup) {
        mcAnswers = radioGroup;
    }

    @Override
    public void receiveMCA(RadioButton radioButton) {
        a = radioButton;
    }

    @Override
    public void receiveMCB(RadioButton radioButton) {
        b = radioButton;
    }

    @Override
    public void receiveMCC(RadioButton radioButton) {
        c = radioButton;
    }

    @Override
    public void receiveMCD(RadioButton radioButton) {
        d = radioButton;
    }
}

package com.example.a10019356.quizproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class QuestionFragment extends Fragment {

    ArrayList<MultipleChoice> mcqs = new ArrayList<>();
    RadioGroup answers;
    RadioButton a;
    RadioButton b;
    RadioButton c;
    RadioButton d;
    TextView question;
    int kid;
    boolean aub=false;
    scoreKeeping rey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question, container, false);

        answers = v.findViewById(R.id.id_answers);
        a = v.findViewById(R.id.id_A);
        b = v.findViewById(R.id.id_B);
        c = v.findViewById(R.id.id_C);
        d = v.findViewById(R.id.id_D);
        question = v.findViewById(R.id.id_questionAsked);

        mcqs = ((MainActivity)this.getActivity()).getMCArrayList();

        kid = ((MainActivity)this.getActivity()).getKid();


        /*makeQuestion();

        if(mcqs.size()>1) {
            kid = rando(0, mcqs.size() - 1);
            if(kid!=0)
                kid--;
        }else kid = 0;
        question.setText(mcqs.get(kid).getQ());*/
        a.setText(mcqs.get(kid).getA1());
        b.setText(mcqs.get(kid).getA2());
        c.setText(mcqs.get(kid).getA3());
        d.setText(mcqs.get(kid).getA4());
        Log.d("RAWR",""+mcqs.get(kid).getQ());

        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(a.isChecked()){
                    if(a.getText().equals(mcqs.get(kid).getS())){
                        aub=true;
                        rey.score(aub);
                    }else aub=false;
                }
                if(b.isChecked()) {
                    if(b.getText().equals(mcqs.get(kid).getS())) {
                        aub = true;
                        rey.score(aub);
                    }else aub=false;
                }
                if(c.isChecked()){
                    if(c.getText().equals(mcqs.get(kid).getS())){
                        aub=true;
                        rey.score(aub);
                    }else aub=false;
                }
                if(d.isChecked()){
                    if(d.getText().equals(mcqs.get(kid).getS())){
                        aub=true;
                        rey.score(aub);
                    }else aub=false;
                }

                //mcqs.remove(kid);

            }
        });
        aub=false;
       // mcqs.remove(kid);

        Log.d("COUNT",""+mcqs.size());



        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        rey = (scoreKeeping)context;
    }

    public interface scoreKeeping{
        public void score(boolean cor);
    }

    public int rando(int start, int end){
        return (int)(Math.random()*((end-start)+1))+start;
    }

    public void makeQuestion(){

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


}

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
    int score;
    int aub;

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
        score = ((MainActivity)this.getActivity()).getScore();
        aub = ((MainActivity)this.getActivity()).getQuest();

        if(aub!=10) {
            question.setText(mcqs.get(kid).getQ());
            a.setText(mcqs.get(kid).getA1());
            b.setText(mcqs.get(kid).getA2());
            c.setText(mcqs.get(kid).getA3());
            d.setText(mcqs.get(kid).getA4());

            answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (a.isChecked()) {
                        if (a.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }
                    if (b.isChecked()) {
                        if (b.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }
                    if (c.isChecked()) {
                        if (c.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }
                    if (d.isChecked()) {
                        if (d.getText().equals(mcqs.get(kid).getS())) {
                            score++;
                        }
                    }


                }
            });
        }

        return v;
    }




}

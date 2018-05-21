package com.example.a10019356.quizproject;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class questionTrueAndFalse extends Fragment {

    TextView questionThatIsAsked;
    RadioGroup options;
    RadioButton trueOption;
    RadioButton falseOption;
    ArrayList<TrueAndFalse> tfqs = new ArrayList<>();
    int tfcount;
    int score;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question_true_and_false, container, false);

        questionThatIsAsked = v.findViewById(R.id.id_tFQuestion);
        options = v.findViewById(R.id.id_groupOfTF);
        trueOption = v.findViewById(R.id.id_trueOpt);
        falseOption = v.findViewById(R.id.id_falseOpt);

        tfqs = ((MainActivity)this.getActivity()).getTFArrayList();
        tfcount = ((MainActivity)this.getActivity()).getTfcount();
        score = ((MainActivity)this.getActivity()).getScore();


        questionThatIsAsked.setText(tfqs.get(tfcount).getQ());
        trueOption.setText(tfqs.get(tfcount).getT());
        falseOption.setText(tfqs.get(tfcount).getF());

        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(trueOption.isChecked()){
                    if(trueOption.getText().equals(tfqs.get(tfcount).getS())){
                        score++;
                    }
                }
                if(falseOption.isChecked()){
                    if(falseOption.getText().equals(tfqs.get(tfcount).getS())){
                        score++;
                    }
                }

            }
        });


        return v;
    }


}

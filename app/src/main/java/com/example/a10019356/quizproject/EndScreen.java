package com.example.a10019356.quizproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EndScreen extends Fragment {

    TextView message;
    int score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_end_screen, container, false);

        message = v.findViewById(R.id.id_finalScore);

        score = ((MainActivity)this.getActivity()).getScore();

        message.setText("You got a score of "+score+"/10.");

        return v;
    }

}

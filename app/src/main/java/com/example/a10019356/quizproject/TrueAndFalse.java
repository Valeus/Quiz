package com.example.a10019356.quizproject;

import android.os.Parcel;
import android.os.Parcelable;

public class TrueAndFalse implements Parcelable{

    String q,t,f,s;

    public TrueAndFalse(String q, String t, String f, String s){
        this.q=q;
        this.t=t;
        this.f=f;
        this.s=s;
    }

    public String getS() {
        return s;
    }

    public String getF() {
        return f;
    }

    public String getQ() {
        return q;
    }

    public String getT() {
        return t;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

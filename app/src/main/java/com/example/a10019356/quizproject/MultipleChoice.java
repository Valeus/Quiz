package com.example.a10019356.quizproject;

import android.os.Parcel;
import android.os.Parcelable;

public class MultipleChoice implements Parcelable {

    String q,a1,a2,a3,a4,s;

    public MultipleChoice(String q, String a1, String a2, String a3, String a4, String s){
        this.q=q;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        this.s=s;
    }

    public String getQ(){
        return q;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public String getS() {
        return s;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

package com.example.mycars.model;

public class Feedback {

    public Feedback(String massage) {
        this.mMassage = massage;
    }

    public Feedback(String massage, boolean success) {
        this.mMassage = massage;
        this.mSuccess = success;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public String getMassage() {
        return this.mMassage;
    }

    private boolean mSuccess = true;
    private String mMassage = "";
}

package com.example.mycars.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritiesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Favorities");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
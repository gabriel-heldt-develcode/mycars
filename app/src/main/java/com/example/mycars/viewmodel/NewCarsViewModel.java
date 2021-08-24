package com.example.mycars.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mycars.model.VehiclesModel;
import com.example.mycars.repository.VehiclesRepository;

import java.util.List;

public class NewCarsViewModel extends AndroidViewModel {

    private final VehiclesRepository mRepository;

    public NewCarsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = VehiclesRepository.getInstance(application.getApplicationContext());
    }

    public void save(VehiclesModel vehicles) {
        this.mRepository.insert(vehicles);
    }
}
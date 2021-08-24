package com.example.mycars.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycars.model.VehiclesModel;
import com.example.mycars.repository.VehiclesRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final VehiclesRepository mRepository;

    private final MutableLiveData<List<VehiclesModel>> mVehicleList = new MutableLiveData<>();
    public LiveData<List<VehiclesModel>> vehicleList = this.mVehicleList;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = VehiclesRepository.getInstance(application.getApplicationContext());
    }

    public void getList() {
        this.mVehicleList.setValue(this.mRepository.getList());
    }
}
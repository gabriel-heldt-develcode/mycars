package com.example.mycars.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mycars.model.VehiclesModel;
import com.example.mycars.repository.VehiclesRepository;

public class NewCarsViewModel extends AndroidViewModel {

    private final VehiclesRepository mRepository;
    private final MutableLiveData<VehiclesModel> mVehicleModel = new MutableLiveData<>();
    public LiveData<VehiclesModel> vehiclesModel = this.mVehicleModel;

    public NewCarsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = VehiclesRepository.getInstance(application.getApplicationContext());
    }

    public void save(VehiclesModel vehicle) {
        if (vehicle.getId() == 0) {
            this.mRepository.insert(vehicle);
        } else {
            this.mRepository.update(vehicle);
        }
    }

    public void load(int id) {
        this.mVehicleModel.setValue(this.mRepository.load(id));
    }
}
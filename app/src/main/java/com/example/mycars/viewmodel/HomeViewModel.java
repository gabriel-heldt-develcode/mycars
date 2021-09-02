package com.example.mycars.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycars.model.Feedback;
import com.example.mycars.model.VehiclesModel;
import com.example.mycars.repository.VehiclesRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final VehiclesRepository mRepository;

    private final MutableLiveData<List<VehiclesModel>> mVehicleList = new MutableLiveData<>();
    public LiveData<List<VehiclesModel>> vehicleList = this.mVehicleList;

    private final MutableLiveData<Feedback> mFeedback = new MutableLiveData<>();
    public LiveData<Feedback> feedback = this.mFeedback;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = VehiclesRepository.getInstance(application.getApplicationContext());
    }

    public void getList() {
        this.mVehicleList.setValue(this.mRepository.getList());
    }

    public void delete(int id) {
        if (this.mRepository.delete(id)) {
            this.mFeedback.setValue(new Feedback("Veículo removido com sucesso!"));
        } else {
            this.mFeedback.setValue(new Feedback("Erro inesperado", false));
        }
    }
}
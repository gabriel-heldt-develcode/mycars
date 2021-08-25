package com.example.mycars.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mycars.model.Feedback;
import com.example.mycars.model.VehiclesModel;
import com.example.mycars.repository.VehiclesRepository;

public class NewCarsViewModel extends AndroidViewModel {

    private final VehiclesRepository mRepository;

    private final MutableLiveData<VehiclesModel> mVehicleModel = new MutableLiveData<>();
    public LiveData<VehiclesModel> vehiclesModel = this.mVehicleModel;

    private final MutableLiveData<Feedback> mFeedback = new MutableLiveData<>();
    public LiveData<Feedback> feedback = this.mFeedback;

    public NewCarsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = VehiclesRepository.getInstance(application.getApplicationContext());
    }

    public void save(VehiclesModel vehicle) {

        if ("".equals(vehicle.getModel())) {
            this.mFeedback.setValue(new Feedback("Informações incompletas", false));
            return;
        }
        if (vehicle.getId() == 0) {
            if (this.mRepository.insert(vehicle)) {
                this.mFeedback.setValue(new Feedback("Veículo inserido com sucesso!"));
            } else {
                this.mFeedback.setValue(new Feedback("Erro inesperado", false));
            }
        } else {
            if (this.mRepository.update(vehicle)) {
                this.mFeedback.setValue(new Feedback("Veículo atualizado com sucesso!"));
            } else {
                this.mFeedback.setValue(new Feedback("Erro inesperado", false));
            }
        }
    }

    public void load(int id) {
        this.mVehicleModel.setValue(this.mRepository.load(id));
    }
}
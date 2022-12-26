package com.example.krankenhaus.ui.doctor.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.repository.PatientRepository;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;

    public DoctorViewModel(@NonNull Application application) {
        super(application);
        patientRepository = PatientRepository.getInstance(application);
        allPatients = patientRepository.getAllPatients();
    }

    public void insert(Patient patient) {
        patientRepository.insertPatient(patient);
    }

    public void update(Patient patient) {
        patientRepository.updatePatient(patient);
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }
}

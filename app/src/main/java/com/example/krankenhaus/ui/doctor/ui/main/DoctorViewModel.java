package com.example.krankenhaus.ui.doctor.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.VisitRepository;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private VisitRepository visitRepository;

    private LiveData<List<Patient>> allPatients;
    private LiveData<List<Visit>> allVisits;

    public MutableLiveData<Patient> patient = new MutableLiveData<>();

    public DoctorViewModel(@NonNull Application application) {
        super(application);

        patientRepository = PatientRepository.getInstance(application);
        allPatients = patientRepository.getAllPatients();

        visitRepository = VisitRepository.getInstance(application);
        allVisits = visitRepository.getAllVisits();
    }

    public void setRepository(PatientRepository patientRepository, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
    }

    public void setPatient(Patient input) {
        patient.setValue(input);
    }

    public LiveData<Patient> getPatient() {
        return patient;
    }

    public void insertPatient(Patient patient) {
        patientRepository.insertPatient(patient);
    }

    public void updatePatient(Patient patient) {
        patientRepository.updatePatient(patient);
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

    public void insertVisit(Visit visit) { visitRepository.insertVisit(visit); }

    public LiveData<List<Visit>> getAllVisits() { return allVisits; }

    public LiveData<Patient> getPatientByInsuranceNumber(String insuranceNumber) { return patientRepository.getPatientByInsuranceNumber(insuranceNumber); }
}

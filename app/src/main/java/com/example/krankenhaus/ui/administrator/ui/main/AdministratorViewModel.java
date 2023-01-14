package com.example.krankenhaus.ui.administrator.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;

import java.util.List;

public class AdministratorViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;
    private LiveData<List<PatientAndBed>> allPatientAndBeds;

    private BedRepository bedRepository;
    private LiveData<List<Bed>> allBeds;
    private LiveData<List<BedAndPatient>> allBedAndPatients;

    public AdministratorViewModel(@NonNull Application application) {
        super(application);

        patientRepository = PatientRepository.getInstance(application);
        allPatients = patientRepository.getAllPatients();
        allPatientAndBeds = patientRepository.getAllPatientAndBeds();

        bedRepository = BedRepository.getInstance(application);
        allBeds = bedRepository.getAllBeds();
        allBedAndPatients = bedRepository.getBedAndPatientLists();
    }

    public void setRepository(PatientRepository patientRepository, BedRepository bedRepository) {
        this.patientRepository = patientRepository;
        this.bedRepository = bedRepository;
    }

    public void insertPatient(Patient patient) { patientRepository.insertPatient(patient); }

    public void updatePatient(Patient patient) { patientRepository.updatePatient(patient); }

    public LiveData<List<Patient>> getAllPatients() { return allPatients; }

    public LiveData<List<PatientAndBed>> getAllPatientAndBeds() { return allPatientAndBeds; }

    public void updateBed(Bed bed) { bedRepository.updateBed(bed); }

    public LiveData<Integer> getNumberOfOccupiedBeds() { return bedRepository.getNumberOfOccupiedBeds(); }

    public LiveData<Integer> getNumberOfTotalBeds() { return bedRepository.getNumberOfTotalBeds(); }

    public LiveData<List<Bed>> getAllBeds() { return allBeds; }

    public LiveData<List<BedAndPatient>> getAllBedAndPatients(){
        return allBedAndPatients;
    }
}

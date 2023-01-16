package com.example.krankenhaus.ui.administrator.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndBloodTestAndMRI;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.srccode.repository.MRIRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;
import com.example.krankenhaus.srccode.repository.VisitRepository;

import java.util.List;

public class AdminViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;
    private LiveData<List<PatientAndBed>> allPatientAndBeds;

    private BedRepository bedRepository;
    private LiveData<List<Bed>> allBeds;
    private LiveData<List<Bed>> allFreeBeds;
    private LiveData<List<BedAndPatient>> allBedAndPatients;

    private RecordRepository recordRepository;
    private BloodTestRepository bloodTestRepository;
    private MRIRepository mriRepository;
    private VisitRepository visitRepository;

    public MutableLiveData<Patient> patient = new MutableLiveData<>();
    public MutableLiveData<PatientAndRecord> patientAndRecord = new MutableLiveData<>();
    public MutableLiveData<RecordAndBloodTestAndMRI> recordAndBloodTestAndMRI = new MutableLiveData<>();
    public MutableLiveData<RecordAndVisitAndPatient> recordAndVisitAndPatient = new MutableLiveData<>();

    public AdminViewModel(@NonNull Application application) {
        super(application);

        patientRepository = PatientRepository.getInstance(application);
        allPatients = patientRepository.getAllPatients();
        allPatientAndBeds = patientRepository.getAllPatientAndBeds();

        bedRepository = BedRepository.getInstance(application);
        allBeds = bedRepository.getAllBeds();
        allFreeBeds = bedRepository.getAllFreeBeds();
        allBedAndPatients = bedRepository.getBedAndPatientLists();

        recordRepository = RecordRepository.getInstance(application);
        bloodTestRepository = BloodTestRepository.getInstance(application);
        mriRepository = MRIRepository.getInstance(application);
        visitRepository = VisitRepository.getInstance(application);
    }

    public void setRepository(PatientRepository patientRepository, BedRepository bedRepository) {
        this.patientRepository = patientRepository;
        this.bedRepository = bedRepository;
    }

    public void setPatient(Patient input) {
        patient.setValue(input);
    }

    public void setPatientAndRecord(PatientAndRecord input) {
        patientAndRecord.setValue(input);
    }

    public void setRecordAndBloodTestAndMRI(RecordAndBloodTestAndMRI inputList){
        recordAndBloodTestAndMRI.setValue(inputList);
    }

    public void setRecordAndVisitAndPatient(RecordAndVisitAndPatient inputList){
        recordAndVisitAndPatient.setValue(inputList);
    }

    public void insertPatient(Patient patient) { patientRepository.insertPatient(patient); }

    public void insertRecord(Record record) { recordRepository.insertRecord(record);}

    public void updatePatient(Patient patient) { patientRepository.updatePatient(patient); }

    public void updateBed(Bed bed) { bedRepository.updateBed(bed); }

    public LiveData<Patient> getPatient() {
        return patient;
    }

    public LiveData<PatientAndRecord> getPatientAndRecord() { return patientAndRecord; }

    public LiveData<RecordAndVisitAndPatient> getRecordAndVisitAndPatient() { return recordAndVisitAndPatient; }

    public LiveData<RecordAndBloodTestAndMRI> getRecordAndBloodTestAndMRI() { return recordAndBloodTestAndMRI;}

    public LiveData<List<Patient>> getAllPatients() { return allPatients; }

    public LiveData<List<PatientAndBed>> getAllPatientAndBeds() { return allPatientAndBeds; }

    public LiveData<List<Bed>> getAllBeds() { return allBeds; }

    public LiveData<List<Bed>> getFreeBeds() { return allFreeBeds; }

    public LiveData<Bed> getNextFreeBed() {
        return Transformations.map(allFreeBeds, (beds) -> beds.stream().findFirst().orElse(null));
    }

    public LiveData<List<BedAndPatient>> getAllBedAndPatients(){
        return allBedAndPatients;
    }

    public LiveData<PatientAndRecord> getPatientAndRecordByInsuranceNumber(String insuranceNumber) {
        return patientRepository.getPatientAndRecordByInsuranceNumber(insuranceNumber);
    }

    public LiveData<RecordAndBloodTestAndMRI> getRecordAndBloodTestAndMRIByInsuranceNumber(String insuranceNumber){
        return recordRepository.getAllRecordAndBloodTestAndMRIByInsuranceNumber(insuranceNumber);
    }

    public LiveData<RecordAndVisitAndPatient> getRecordAndVisitAndPatientByInsuranceNumber (String insuranceNumber){
        return recordRepository.getRecordAndPatientAndVisitByInsuranceNumber(insuranceNumber);
    }

    public LiveData<Integer> getNumberOfOccupiedBeds() { return bedRepository.getNumberOfOccupiedBeds(); }

    public LiveData<Integer> getNumberOfTotalBeds() { return bedRepository.getNumberOfTotalBeds(); }

    public void dischargePatient(RecordAndVisitAndPatient recordAndVisitAndPatient, RecordAndBloodTestAndMRI recordAndBloodTestAndMRI){
        for(Visit visit : recordAndVisitAndPatient.visits){
            if(visit == null){
                continue;
            }
            visitRepository.deleteVisit(visit);
        }
        for(BloodTest bloodTest : recordAndBloodTestAndMRI.bloodTest){
            if(bloodTest == null){
                continue;
            }
            bloodTestRepository.deleteBloodTest(bloodTest);
        }
        for(MRI mri : recordAndBloodTestAndMRI.mri){
            if(mri == null){
                continue;
            }
            mriRepository.deleteMRI(mri);
        }
        recordRepository.deleteRecord(recordAndVisitAndPatient.record);
        patientRepository.deletePatient(recordAndVisitAndPatient.patient);
    }
}

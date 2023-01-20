package com.example.krankenhaus.ui.doctor.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.srccode.repository.MRIRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;
import com.example.krankenhaus.srccode.repository.VisitRepository;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private VisitRepository visitRepository;
    private RecordRepository recordRepository;

    private MRIRepository mriRepository;
    private BloodTestRepository bloodTestRepository;

    private LiveData<List<Patient>> allPatients;
    private LiveData<List<PatientAndBed>> allPatientAndBeds;
    private LiveData<List<Visit>> allVisits;
    private LiveData<List<VisitAndRecord>> allVisitAndRecords;

    private LiveData<List<MRIAndRecord>> allDoneMriAndRecords;
    private LiveData<List<BloodTestAndRecord>> allDoneBloodTestAndRecords;

    public MutableLiveData<PatientAndRecord> patientAndRecord = new MutableLiveData<>();
    public MutableLiveData<RecordAndPatient> recordAndPatient = new MutableLiveData<>();

    public MutableLiveData<BloodTest> bloodTestResult = new MutableLiveData<>();
    public MutableLiveData<MRI> MRIResult = new MutableLiveData<>();

    public MutableLiveData<RecordWithAll> recordWithAllByInsuranceNumber = new MutableLiveData<>();
    public DoctorViewModel(@NonNull Application application) {
        super(application);

        patientRepository = PatientRepository.getInstance(application);
        allPatients = patientRepository.getAllPatients();
        allPatientAndBeds = patientRepository.getAllPatientAndBeds();

        mriRepository = MRIRepository.getInstance(application);
        bloodTestRepository = BloodTestRepository.getInstance(application);
        allDoneMriAndRecords = mriRepository.getAllDoneMRIAndRecord();
        allDoneBloodTestAndRecords = bloodTestRepository.getAllDoneBloodTestAndRecord();

        visitRepository = VisitRepository.getInstance(application);
        allVisits = visitRepository.getAllVisits();
        allVisitAndRecords = visitRepository.getAllVisitAndRecord();

        recordRepository = RecordRepository.getInstance(application);
    }

    public void setRepository(PatientRepository patientRepository, VisitRepository visitRepository, RecordRepository recordRepository) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.recordRepository = recordRepository;
    }

    public void setPatientAndRecord(PatientAndRecord input) { patientAndRecord.setValue(input); }

    public void setRecordAndPatient(RecordAndPatient input) { recordAndPatient.setValue(input); }

    public void setBloodTestResult(BloodTest input) {
        bloodTestResult.setValue(input);
    }

    public void setMRIResult(MRI input) {
        MRIResult.setValue(input);
    }

    public MutableLiveData<PatientAndRecord> getPatientAndRecord() { return patientAndRecord; }

    public MutableLiveData<RecordAndPatient> getRecordAndPatient() { return recordAndPatient; }

    public void insertPatient(Patient patient) {
        patientRepository.insertPatient(patient);
    }

    public void updatePatient(Patient patient) {
        patientRepository.updatePatient(patient);
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

    public LiveData<List<PatientAndBed>> getAllPatientAndBeds() {return allPatientAndBeds;}

    public LiveData<List<VisitAndRecord>> getAllVisitAndRecords() { return allVisitAndRecords; }

    public LiveData<List<MRIAndRecord>> getAllDoneMriAndRecords() { return allDoneMriAndRecords; }

    public LiveData<List<BloodTestAndRecord>> getAllDoneBloodTestAndRecords() { return allDoneBloodTestAndRecords; }

    public void insertVisit(Visit visit) { visitRepository.insertVisit(visit); }

    public LiveData<List<Visit>> getAllVisits() { return allVisits; }

    public LiveData<PatientAndRecord> getPatientAndRecordByInsuranceNumber(String insuranceNumber) { return patientRepository.getPatientAndRecordByInsuranceNumber(insuranceNumber); }

    public LiveData<RecordAndVisitAndPatient> getRecordAndVisitAndPatientByInsuranceNumber(String insuranceNumber) { return recordRepository.getRecordAndPatientAndVisitByInsuranceNumber(insuranceNumber); }

    public LiveData<List<RecordWithAll>> getRecordWithAllByRecordId(int recordId) { return recordRepository.getRecordWithAllByRecordId(recordId); }

    public LiveData<RecordAndPatient> getRecordAndPatientByRecordID(int recordID) { return recordRepository.getRecordAndPatientByRecordID(recordID); }

    public void insertExamination(MRI mri, BloodTest bloodTest) {
        mriRepository.insertMRI(mri);
        bloodTestRepository.insertBloodTest(bloodTest);
    }

    public LiveData<MRI> getAllMRIByRecordID(int recordID) { return mriRepository.getAllMRIByRecordID(recordID); }

    public LiveData<BloodTest> getAllBloodTestByRecordID(int recordID) { return bloodTestRepository.getAllBloodTestByRecordID(recordID); }

    public LiveData<BloodTest> getBloodTestResult() {
        return bloodTestResult;
    }

    public LiveData<MRI> getMRIAndRecordResult() {
        return MRIResult;
    }

    public LiveData<RecordWithAll> getRecordWithAllByInsuranceNumber(String insuranceNumber){
        return recordRepository.getRecordWithAllByInsuranceNumber(insuranceNumber);
    }

    public void setRecordWithAllByInsuranceNumber(RecordWithAll input){
        recordWithAllByInsuranceNumber.setValue(input);
    }

    public LiveData<RecordWithAll> getRecordWithAllByInsuranceNumber() {
        return recordWithAllByInsuranceNumber;
    }
}

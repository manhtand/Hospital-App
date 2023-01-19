package com.example.krankenhaus.ui.service.labor.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.srccode.repository.MRIRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;

public class LaborViewModel extends AndroidViewModel {
    private BloodTestRepository bloodTestRepository;
    private MRIRepository mriRepository;
    private RecordRepository recordRepository;
    private LiveData<List<BloodTestAndRecord>> allNewBloodTest;
    private LiveData<List<MRIAndRecord>> allNewMRI;

    private LiveData<List<MRIAndRecord>> allDoneMRI;

    public MutableLiveData<BloodTestAndRecord> bloodTestAndRecord = new MutableLiveData<>();
    public MutableLiveData<MRIAndRecord> mriAndRecord = new MutableLiveData<>();

    List<BloodTest> bloodTestSource;

    public LaborViewModel(@NonNull Application application) {
        super(application);
        bloodTestRepository = BloodTestRepository.getInstance(application);
        allNewBloodTest = bloodTestRepository.getAllNewBloodTestAndRecord();

        mriRepository = MRIRepository.getInstance(application);
        allNewMRI = mriRepository.getAllNewMRIAndRecord();

        allDoneMRI=mriRepository.getAllDoneMRIAndRecord();

        recordRepository = RecordRepository.getInstance(application);
    }

    public void setBloodTestAndRecord(BloodTestAndRecord input) {
        bloodTestAndRecord.setValue(input);
    }

    public void setMriAndRecord(MRIAndRecord input) {
        mriAndRecord.setValue(input);
    }

    public LiveData<List<BloodTestAndRecord>> getAllNewBloodTestAndRecord() {
        return allNewBloodTest;
    }

    public LiveData<List<MRIAndRecord>> getAllNewMRIAndRecord(){
        return allNewMRI;
    }

    public LiveData<List<MRIAndRecord>> getAllDoneMRIAndRecord(){
        return allDoneMRI;
    }

    public LiveData<BloodTestAndRecord> getBloodTestAndRecord() {
        return bloodTestAndRecord;
    }

    public LiveData<RecordAndPatient> getRecordAndPatientByRecordID(int recordID) {
        return recordRepository.getRecordAndPatientByRecordID(recordID);
    }

    public LiveData<MRIAndRecord> getMriAndRecord() {
        return mriAndRecord;
    }

    public void updateMRI(MRI mri){
        mriRepository.updateMRI(mri);
    }

    public void updateBloodTest(BloodTest bloodTest) {
        bloodTestRepository.updateBloodTest(bloodTest);
    }

    public void setBloodTestSource(List<BloodTest> input) { this.bloodTestSource = input; }

    public List<BloodTest> getBloodTestSource() { return bloodTestSource; }
}
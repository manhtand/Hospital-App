package com.example.krankenhaus.ui.service.labor.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.srccode.repository.BloodTestRepository;
import com.example.krankenhaus.srccode.repository.MRIRepository;
import com.example.krankenhaus.srccode.repository.RecordRepository;

import java.util.List;

public class LaborViewModel extends AndroidViewModel {
    private BloodTestRepository bloodTestRepository;
    private MRIRepository mriRepository;
    private RecordRepository recordRepository;
    private LiveData<List<BloodTestAndRecord>> allNewBloodTest;
    private LiveData<List<MRIAndRecord>> allNewMRI;

    public LaborViewModel(@NonNull Application application) {
        super(application);
        bloodTestRepository = BloodTestRepository.getInstance(application);
        allNewBloodTest = bloodTestRepository.getAllNewBloodTest();

        mriRepository = MRIRepository.getInstance(application);
        allNewMRI = mriRepository.getAllNewMRI();

        recordRepository = RecordRepository.getInstance(application);
    }

    public LiveData<List<BloodTestAndRecord>> getAllNewBloodTest(){
        return allNewBloodTest;
    }

    public LiveData<List<MRIAndRecord>> getAllNewMRI(){
        return allNewMRI;
    }
}
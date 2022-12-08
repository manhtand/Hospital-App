package com.example.krankenhaus.ui.doctor.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DoctorDashboardViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public DoctorDashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Doctor Dashboard Fragment");
    }

    public LiveData<String> getText() { return mText; }
}
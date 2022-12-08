package com.example.krankenhaus.ui.doctor.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DoctorNotificationsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public DoctorNotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Doctor Notifications Fragment");
    }

    public LiveData<String> getText() { return mText; }
}
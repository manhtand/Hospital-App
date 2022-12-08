package com.example.krankenhaus.ui.administrator.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AdministratorDashboardViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AdministratorDashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Administrator Dashboard Fragment");
    }

    public LiveData<String> getText() { return mText; }
}
package com.example.krankenhaus.ui.service.labor.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LaborDashboardViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public LaborDashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Labor Dashboard Fragment");
    }

    public LiveData<String> getText() { return mText; }
}
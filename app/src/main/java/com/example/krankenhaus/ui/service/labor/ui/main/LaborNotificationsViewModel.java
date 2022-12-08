package com.example.krankenhaus.ui.service.labor.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LaborNotificationsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public LaborNotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Labor Notifications Fragment");
    }

    public LiveData<String> getText() { return mText; }
}
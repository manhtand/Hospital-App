package com.example.krankenhaus.ui.administrator.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AdministratorNotificationsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AdministratorNotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Administrator Notifications Fragment");
    }

    public LiveData<String> getText() { return mText; }
}
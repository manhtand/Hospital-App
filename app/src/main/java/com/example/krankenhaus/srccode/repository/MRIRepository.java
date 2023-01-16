package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.MRIDao;
import com.example.krankenhaus.srccode.entities.MRI;

public class MRIRepository {
    private volatile static MRIRepository INSTANCE = null;
    private final MRIDao mriDao;

    public MRIRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        mriDao = hospitalDatabase.mriDao();
    }

    public static synchronized MRIRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new MRIRepository(application);
        }
        return INSTANCE;
    }

    public void insertMRI(MRI mri){
        new InsertMRIAsyncTask(mriDao).execute(mri);
    }

    public void deleteMRI(MRI mri) {
        mriDao.deleteMRI(mri);
    }

    public LiveData<MRI> getAllMRIByRecordID(int recordID){
        return mriDao.getAllMRIByRecordID(recordID);
    }

    private static class InsertMRIAsyncTask extends AsyncTask<MRI,Void,Void> {
        private MRIDao mriDao;

        private InsertMRIAsyncTask(MRIDao mriDao){
            this.mriDao = mriDao;
        }

        @Override
        protected Void doInBackground(MRI... mris) {
            mriDao.insertMRT(mris[0]);
            return null;
        }
    }
}

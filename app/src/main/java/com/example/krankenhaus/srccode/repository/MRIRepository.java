package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

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

    public void insertMRT(MRI mrt){
        new InsertMRIAsyncTask(mriDao).execute(mrt);
    }

    private static class InsertMRIAsyncTask extends AsyncTask<MRI,Void,Void> {
        private MRIDao mriDao;

        private InsertMRIAsyncTask(MRIDao mrtDao){
            this.mriDao = mrtDao;
        }

        @Override
        protected Void doInBackground(MRI... mrts) {
            mriDao.insertMRT(mrts[0]);
            return null;
        }
    }
}

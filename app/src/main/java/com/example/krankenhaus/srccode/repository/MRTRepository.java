package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.MRTDao;
import com.example.krankenhaus.srccode.entities.MRT;

public class MRTRepository {
    private final MRTDao mrtDao;

    public MRTRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        mrtDao = hospitalDatabase.mrtDao();
    }

    public void insertMRT(MRT mrt){
        new InsertMRTAsyncTask(mrtDao).execute(mrt);
    }

    private static class InsertMRTAsyncTask extends AsyncTask<MRT,Void,Void> {
        private MRTDao mrtDao;

        private InsertMRTAsyncTask(MRTDao mrtDao){
            this.mrtDao = mrtDao;
        }

        @Override
        protected Void doInBackground(MRT... mrts) {
            mrtDao.insertMRT(mrts[0]);
            return null;
        }
    }
}

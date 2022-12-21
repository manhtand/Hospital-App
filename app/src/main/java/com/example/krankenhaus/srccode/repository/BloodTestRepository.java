package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Delete;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.BloodTestDao;
import com.example.krankenhaus.srccode.entities.BloodTest;

import io.reactivex.Completable;

public class BloodTestRepository {
    private final BloodTestDao bloodTestDao;

    public BloodTestRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        bloodTestDao = hospitalDatabase.bloodTestDao();
    }

    public void insertBloodTest(BloodTest bloodTest){
        new InsertBloodTestAsyncTask(bloodTestDao).execute(bloodTest);
    }

    public void deleteBloodTest(BloodTest bloodTest){
        new DeleteBloodTestAsyncTask(bloodTestDao).execute(bloodTest);
    }

    private static class InsertBloodTestAsyncTask extends AsyncTask<BloodTest,Void,Void> {
        private BloodTestDao bloodTestDao;

        private InsertBloodTestAsyncTask(BloodTestDao bloodTestDao){
            this.bloodTestDao = bloodTestDao;
        }

        @Override
        protected Void doInBackground(BloodTest... bloodTests) {
            bloodTestDao.insertBloodTest(bloodTests[0]);
            return null;
        }
    }

    private static class DeleteBloodTestAsyncTask extends AsyncTask<BloodTest,Void,Void> {
        private BloodTestDao bloodTestDao;

        private DeleteBloodTestAsyncTask(BloodTestDao bloodTestDao){
            this.bloodTestDao = bloodTestDao;
        }

        @Override
        protected Void doInBackground(BloodTest... bloodTests) {
            bloodTestDao.deleteBloodTest(bloodTests[0]);
            return null;
        }
    }
}

package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.BloodTestDao;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;

import java.util.List;

import io.reactivex.Completable;

public class BloodTestRepository {
    private volatile static BloodTestRepository INSTANCE = null;
    private final BloodTestDao bloodTestDao;
    private LiveData<List<BloodTestAndRecord>> allNewBloodTestAndRecord;
    private LiveData<List<BloodTestAndRecord>> allDoneBloodTestAndRecord;

    private BloodTestRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        bloodTestDao = hospitalDatabase.bloodTestDao();
        allNewBloodTestAndRecord = bloodTestDao.getAllNewBloodTestAndRecord();
        allDoneBloodTestAndRecord = bloodTestDao.getAllDoneBloodTestAndRecord();
    }

    public static synchronized BloodTestRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new BloodTestRepository(application);
        }
        return INSTANCE;
    }

    public void insertBloodTest(BloodTest bloodTest){
        new InsertBloodTestAsyncTask(bloodTestDao).execute(bloodTest);
    }

    public void updateBloodTest(BloodTest bloodTest){
        new UpdateBloodTestAsyncTask(bloodTestDao).execute(bloodTest);
    }

    public void deleteBloodTest(BloodTest bloodTest){
        new DeleteBloodTestAsyncTask(bloodTestDao).execute(bloodTest);
    }

    public LiveData<List<BloodTestAndRecord>> getAllNewBloodTestAndRecord(){
        return allNewBloodTestAndRecord;
    }

    public LiveData<List<BloodTestAndRecord>> getAllDoneBloodTestAndRecord() { return allDoneBloodTestAndRecord; }

    public LiveData<BloodTest> getAllBloodTestByRecordID(int recordID){
        return bloodTestDao.getAllBloodTestByRecordID(recordID);
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

    private static class UpdateBloodTestAsyncTask extends AsyncTask<BloodTest,Void,Void> {
        private BloodTestDao bloodTestDao;

        private UpdateBloodTestAsyncTask(BloodTestDao bloodTestDao){
            this.bloodTestDao = bloodTestDao;
        }

        @Override
        protected Void doInBackground(BloodTest... bloodTests) {
            bloodTestDao.updateBloodTest(bloodTests[0]);
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

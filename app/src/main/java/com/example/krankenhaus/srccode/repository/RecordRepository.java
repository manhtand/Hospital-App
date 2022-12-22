package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.RecordDao;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;

import java.util.List;

public class RecordRepository {
    private volatile static RecordRepository INSTANCE = null;
    private final RecordDao recordDao;
    private LiveData<List<Record>> allRecords;

    public RecordRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        recordDao = hospitalDatabase.recordDao();
    }

    public static synchronized RecordRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RecordRepository(application);
        }
        return INSTANCE;
    }

    public void insertRecord(Record record){
        new InsertRecordAsyncTask(recordDao).execute(record);
    }

    public void updateRecord(Record record){
        new UpdateRecordAsyncTask(recordDao).execute(record);
    }

    public LiveData<List<Record>> getAllRecords(){
        return allRecords;
    }

    public LiveData<RecordWithAll> getRecordWithAllByRecordId(int recordId){
        try{
            return new getRecordWithAllByRecordIdAsyncTask(recordDao).execute(recordId).get();
        }
        finally {
            return null;
        }
    }

    private static class InsertRecordAsyncTask extends AsyncTask<Record,Void,Void> {
        private RecordDao recordDao;

        private InsertRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insertRecord(records[0]);
            return null;
        }
    }

    private static class UpdateRecordAsyncTask extends AsyncTask<Record,Void,Void> {
        private RecordDao recordDao;

        private UpdateRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.updateRecord(records[0]);
            return null;
        }
    }

    private static class getRecordWithAllByRecordIdAsyncTask extends AsyncTask<Integer,Integer,LiveData<RecordWithAll>> {
        private RecordDao recordDao;

        private getRecordWithAllByRecordIdAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected LiveData<RecordWithAll> doInBackground(Integer... integers) {
            return recordDao.getRecordWithAllByRecordId(integers[0]);
        }
    }
}

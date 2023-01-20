package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.RecordDao;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.RecordAndBloodTestAndMRI;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;

import java.util.List;

public class RecordRepository {
    private volatile static RecordRepository INSTANCE = null;
    private final RecordDao recordDao;
    private LiveData<List<Record>> allRecords;
    private LiveData<List<RecordAndVisitAndPatient>> allRecordAndPatientAndVisits;

    public RecordRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        recordDao = hospitalDatabase.recordDao();
        allRecords =  recordDao.getAllRecords();
        allRecordAndPatientAndVisits = recordDao.getAllRecordAndPatientAndVisit();
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

    public void deleteRecord(Record record){
        new DeleteRecordAsyncTask(recordDao).execute(record);
    }

    public LiveData<List<Record>> getAllRecords(){
        return allRecords;
    }

    public LiveData<List<RecordWithAll>> getRecordWithAllByRecordId(int recordId){
        return recordDao.getRecordWithAllByRecordId(recordId);
    }

    public LiveData<RecordAndVisitAndPatient> getRecordAndPatientAndVisitByInsuranceNumber(String insuranceNumber){
        return recordDao.getRecordAndPatientAndVisitByInsuranceNumber(insuranceNumber);
    }

    public LiveData<List<RecordAndVisitAndPatient>> getAllRecordAndPatientAndVisit(){
        return allRecordAndPatientAndVisits;
    }

    public LiveData<RecordAndBloodTestAndMRI> getAllRecordAndBloodTestAndMRIByInsuranceNumber(String insuranceNumber){
        return recordDao.getAllRecordAndBloodTestAndMRIByInsuranceNumber(insuranceNumber);
    }

    public LiveData<RecordAndPatient> getRecordAndPatientByRecordID(int recordID) {
        return recordDao.getRecordAndPatientByRecordID(recordID);
    }

    public LiveData<RecordWithAll> getRecordWithAllByInsuranceNumber(String insuranceNumber) {
        return recordDao.getRecordWithAllByInsuranceNumber(insuranceNumber);
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

    private static class DeleteRecordAsyncTask extends AsyncTask<Record,Void,Void> {
        private RecordDao recordDao;

        private DeleteRecordAsyncTask(RecordDao recordDao){
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.deleteRecord(records[0]);
            return null;
        }
    }

    /*private static class getRecordWithAllByRecordIdAsyncTask extends AsyncTask<Integer,Integer,LiveData<List<RecordWithAll>>> {
        private RecordDao recordDao;

        private getRecordWithAllByRecordIdAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected LiveData<List<RecordWithAll>> doInBackground(Integer... integers) {
            return recordDao.getRecordWithAllByRecordId(integers[0]);
        }
    }

    private static class getRecordAndPatientAndVisitByInsuranceNumberAsyncTask extends AsyncTask<String,String,LiveData<RecordAndVisitAndPatient>> {
        private RecordDao recordDao;

        private getRecordAndPatientAndVisitByInsuranceNumberAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected LiveData<RecordAndVisitAndPatient> doInBackground(String... strings) {
            return recordDao.getRecordAndPatientAndVisitByInsuranceNumber(strings[0]);
        }
    }*/
}

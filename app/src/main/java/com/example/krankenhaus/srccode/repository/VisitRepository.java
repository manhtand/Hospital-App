package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.VisitDao;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;

import java.util.List;

public class VisitRepository {
    private volatile static VisitRepository INSTANCE = null;
    private final VisitDao visitDao;
    private LiveData<List<Visit>> allVisit;
    private LiveData<List<VisitAndRecord>> allVisitAndRecord;

    public VisitRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        visitDao = hospitalDatabase.visitDao();
        allVisit = visitDao.getAllVisits();
        allVisitAndRecord = visitDao.getAllVisitAndRecord();
    }

    public static synchronized VisitRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new VisitRepository(application);
        }
        return INSTANCE;
    }

    public void insertVisit(Visit visit){
        new InsertRecordAsyncTask(visitDao).execute(visit);
    }

    public void deleteVisit(Visit visit){
        new DeleteRecordAsyncTask(visitDao).execute(visit);
    }

    public LiveData<List<Visit>> getAllVisits(){
        return allVisit;
    }

    public LiveData<List<Visit>> getAllVisitByRecordID(int recordID){
        return visitDao.getAllVisitByRecordID(recordID);
    }

    public LiveData<List<VisitAndRecord>> getAllVisitAndRecord(){
        return allVisitAndRecord;
    }

    private static class InsertRecordAsyncTask extends AsyncTask<Visit,Void,Void> {
        private VisitDao visitDao;

        private InsertRecordAsyncTask(VisitDao visitDao){
            this.visitDao = visitDao;
        }

        @Override
        protected Void doInBackground(Visit... visits) {
            visitDao.insertVisit(visits[0]);
            return null;
        }
    }

    private static class DeleteRecordAsyncTask extends AsyncTask<Visit,Void,Void> {
        private VisitDao visitDao;

        private DeleteRecordAsyncTask(VisitDao visitDao){
            this.visitDao = visitDao;
        }

        @Override
        protected Void doInBackground(Visit... visits) {
            visitDao.deleteVisit(visits[0]);
            return null;
        }
    }
}

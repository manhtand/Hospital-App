package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.ExaminationAssignmentDao;
import com.example.krankenhaus.srccode.entities.ExaminationAssignment;

import java.util.List;

public class ExaminationAssignmentRepository {
    private volatile static ExaminationAssignmentRepository INSTANCE = null;
    private final ExaminationAssignmentDao examinationAssigmentDao;
    private LiveData<List<ExaminationAssignment>> examinationAssignmentList;

    private ExaminationAssignmentRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        examinationAssigmentDao = hospitalDatabase.examinationAssigmentDao();
    }

    public static synchronized ExaminationAssignmentRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new ExaminationAssignmentRepository(application);
        }
        return INSTANCE;
    }

    public void insertExaminationAssignment(ExaminationAssignment examinationAssignment){
        new InsertExaminationAssignmentAsyncTask(examinationAssigmentDao).execute(examinationAssignment);
    }

    public void deleteExaminationAssignment(ExaminationAssignment examinationAssignment){
        new DeleteExaminationAssignmentAsyncTask(examinationAssigmentDao).execute(examinationAssignment);
    }

    public LiveData<List<ExaminationAssignment>> getAllExaminationAssignments(){
        return examinationAssignmentList;
    }

    private static class InsertExaminationAssignmentAsyncTask extends AsyncTask<ExaminationAssignment,Void,Void> {
        private ExaminationAssignmentDao examinationAssignmentDao;

        private InsertExaminationAssignmentAsyncTask(ExaminationAssignmentDao examinationAssignmentDao){
            this.examinationAssignmentDao = examinationAssignmentDao;
        }

        @Override
        protected Void doInBackground(ExaminationAssignment... examinationAssignments) {
            examinationAssignmentDao.insertExaminationAssignment(examinationAssignments[0]);
            return null;
        }
    }
    private static class DeleteExaminationAssignmentAsyncTask extends AsyncTask<ExaminationAssignment,Void,Void> {
        private ExaminationAssignmentDao examinationAssignmentDao;

        private DeleteExaminationAssignmentAsyncTask(ExaminationAssignmentDao examinationAssignmentDao){
            this.examinationAssignmentDao = examinationAssignmentDao;
        }

        @Override
        protected Void doInBackground(ExaminationAssignment... examinationAssignments) {
            examinationAssignmentDao.deleteExaminationAssignment(examinationAssignments[0]);
            return null;
        }
    }
}

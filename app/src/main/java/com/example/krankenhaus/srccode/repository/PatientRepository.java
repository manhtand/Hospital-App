package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.PatientDao;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;

import java.util.List;

public class PatientRepository {
    private final PatientDao patientDao;
    private LiveData<List<Patient>> allPatients;

    public PatientRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        patientDao = hospitalDatabase.patientDao();
        allPatients = patientDao.getAllPatients();
    }

    public void insertPatient(Patient patient){
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public void updatePatient(Patient patient){
        new UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    public LiveData<List<Patient>> getAllPatients(){
        return allPatients;
    }

    public LiveData<Patient> getPatientByInsuranceNumber(String insuranceNumber){
        try {
            return new getPatientByInsuranceNumberAsyncTask(patientDao).execute(insuranceNumber).get();
        }
        finally {
            return null;
        }
    }

    public LiveData<PatientAndRecord> getPatientAndRecordByInsuranceNumber(String insuranceNumber){
        try{
            return new getPatientAndRecordByInsuranceNumberAsyncTask(patientDao).execute(insuranceNumber).get();
        }
        finally {
            return null;
        }
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient,Void,Void> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.insertPatient(patients[0]);
            return null;
        }
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<Patient,Void,Void> {
        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.updatePatient(patients[0]);
            return null;
        }
    }

    private static class getPatientByInsuranceNumberAsyncTask extends AsyncTask<String,String,LiveData<Patient>> {
        private PatientDao patientDao;

        private getPatientByInsuranceNumberAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected LiveData<Patient> doInBackground(String... strings) {
            return patientDao.getPatientByInsuranceNumber(strings[0]);
        }
    }

    private static class getPatientAndRecordByInsuranceNumberAsyncTask extends AsyncTask<String,String,LiveData<PatientAndRecord>> {
        private PatientDao patientDao;

        private getPatientAndRecordByInsuranceNumberAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected LiveData<PatientAndRecord> doInBackground(String... strings) {
            return patientDao.getPatientAndRecordByInsuranceNumber(strings[0]);
        }
    }
}

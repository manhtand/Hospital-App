package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.MedicationDao;
import com.example.krankenhaus.srccode.entities.Medication;

public class MedicationRepository {
    private volatile static MedicationRepository INSTANCE = null;
    private final MedicationDao medicationDao;

    public MedicationRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        medicationDao = hospitalDatabase.medicationDao();
    }

    public static synchronized MedicationRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new MedicationRepository(application);
        }
        return INSTANCE;
    }

    public void insertMedication(Medication medication){
        new InsertMedicationAsyncTask(medicationDao).execute(medication);
    }

    private static class InsertMedicationAsyncTask extends AsyncTask<Medication,Void,Void> {
        private MedicationDao medicationDao;

        private InsertMedicationAsyncTask(MedicationDao medicationDao){
            this.medicationDao = medicationDao;
        }

        @Override
        protected Void doInBackground(Medication... medications) {
            medicationDao.insertMedication(medications[0]);
            return null;
        }
    }
}

package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.BedDao;
import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;

import io.reactivex.Flowable;

public class BedRepository {
    private final BedDao bedDao;
    private volatile static BedRepository INSTANCE = null;
    private LiveData<Integer> NumberOfOccupiedBeds;
    private LiveData<Integer> NumberOfTotalBeds;
    private LiveData<Integer> NumberOfFreelBeds;
    private LiveData<List<Bed>> FreeBedList;
    private LiveData<List<Bed>> BedList;
    private LiveData<String> patientName;
    private LiveData<List<BedAndPatient>> BedAndPatientLists;

    private BedRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        bedDao = hospitalDatabase.bedDao();
        NumberOfOccupiedBeds = bedDao.getNumberOfOccupiedBeds();
        NumberOfTotalBeds = bedDao.getNumberOfTotalBeds();
        NumberOfFreelBeds = bedDao.getNumberOfFreeBeds();
        FreeBedList = bedDao.getAllFreeBeds();
        BedList = bedDao.getAllBeds();
        patientName = bedDao.getPatientNameFromBed();
        BedAndPatientLists = bedDao.getAllBedAndPatient();
    }

    public static synchronized BedRepository getInstance(Application application) {
        if (INSTANCE == null) {
                    INSTANCE = new BedRepository(application);
        }
        return INSTANCE;
    }

    public void insertBed(Bed bed) {
        new InsertBedAsyncTask(bedDao).execute(bed);
    }

    public void updateBed(Bed bed) {
        new UpdateBedAsyncTask(bedDao).execute(bed);
    }

    public void deleteBed(Bed bed) { new DeleteBedAsyncTask(bedDao).execute(bed); }

    public LiveData<List<Bed>> getAllBeds(){
        return BedList;
    }

    public LiveData<List<Bed>> getAllFreeBeds(){
        return FreeBedList;
    }

    public LiveData<Integer> getNumberOfFreeBeds() {
        return NumberOfFreelBeds;
    }

    public LiveData<Integer> getNumberOfOccupiedBeds() {
        return NumberOfTotalBeds;
    }

    public LiveData<Integer> getNumberOfTotalBeds() {
        return NumberOfTotalBeds;
    }

    public LiveData<List<BedAndPatient>> getBedAndPatientLists() {
        return BedAndPatientLists;
    }

    private static class InsertBedAsyncTask extends AsyncTask<Bed,Void,Void>{
        private BedDao bedDao;

        private InsertBedAsyncTask(BedDao bedDao){
            this.bedDao = bedDao;
        }

        @Override
        protected Void doInBackground(Bed... beds) {
            bedDao.insertBed(beds[0]);
            return null;
        }
    }
    private static class UpdateBedAsyncTask extends AsyncTask<Bed,Void,Void>{
        private BedDao bedDao;

        private UpdateBedAsyncTask(BedDao bedDao){
            this.bedDao = bedDao;
        }

        @Override
        protected Void doInBackground(Bed... beds) {
            bedDao.updateBed(beds[0]);
            return null;
        }
    }

    private static class DeleteBedAsyncTask extends AsyncTask<Bed,Void,Void>{
        private BedDao bedDao;

        private DeleteBedAsyncTask(BedDao bedDao){
            this.bedDao = bedDao;
        }

        @Override
        protected Void doInBackground(Bed... beds) {
            bedDao.deleteBed(beds[0]);
            return null;
        }
    }
}

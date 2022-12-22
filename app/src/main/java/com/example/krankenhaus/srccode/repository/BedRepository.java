package com.example.krankenhaus.srccode.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

import com.example.krankenhaus.srccode.HospitalDatabase;
import com.example.krankenhaus.srccode.dao.BedDao;
import com.example.krankenhaus.srccode.entities.Bed;

import io.reactivex.Flowable;

public class BedRepository {
    private final BedDao bedDao;
    private volatile static BedRepository INSTANCE = null;
    private LiveData<Integer> NumberOfOccupiedBeds;
    private LiveData<Integer> NumberOfTotalBeds;
    private LiveData<List<Bed>> BedList;

    private BedRepository(Application application) {
        HospitalDatabase hospitalDatabase = HospitalDatabase.getInstance(application);
        bedDao = hospitalDatabase.bedDao();
        NumberOfOccupiedBeds = bedDao.getNumberOfOccupiedBeds();
        NumberOfTotalBeds = bedDao.getNumberOfTotalBeds();
        BedList = bedDao.getAllBeds();
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

    public LiveData<List<Bed>> getAllBeds(){
        return BedList;
    }

    public LiveData<Integer> getNumberOfOccupiedBeds() {
        return NumberOfTotalBeds;
    }

    public LiveData<Integer> getNumberOfTotalBeds() {
        return NumberOfTotalBeds;
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
}

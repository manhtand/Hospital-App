package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.krankenhaus.srccode.entities.Bed;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBed(Bed bed);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateBed(Bed bed);

    @Query("SELECT * FROM bed_table ORDER BY bed_table.number ASC")
    public LiveData<List<Bed>> getAllBeds();

    @Query("SELECT COUNT(*) FROM bed_table b WHERE b.patient_insurance_number IS NULL")
    public LiveData<Integer> getNumberOfOccupiedBeds();

    @Query("SELECT COUNT(*) FROM bed_table")
    public LiveData<Integer> getNumberOfTotalBeds();
}

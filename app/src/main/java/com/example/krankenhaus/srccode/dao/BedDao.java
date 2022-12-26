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

@Dao
public interface BedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBed(Bed bed);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBed(Bed bed);

    @Query("SELECT * FROM bed_table b WHERE b.number NOT IN (SELECT p.bed_number FROM patient_table p) ORDER BY b.number")
    LiveData<List<Bed>> getAllFreeBeds();

    @Query("SELECT * FROM bed_table ORDER BY bed_table.number ASC")
    LiveData<List<Bed>> getAllBeds();

    @Query("SELECT COUNT(*) FROM bed_table b WHERE b.number NOT IN (SELECT p.bed_number FROM patient_table p)")
    LiveData<Integer> getNumberOfFreeBeds();

    @Query("SELECT COUNT(*) FROM bed_table b WHERE b.number IN (SELECT p.bed_number FROM patient_table p)")
    LiveData<Integer> getNumberOfOccupiedBeds();

    @Query("SELECT COUNT(*) FROM bed_table")
    LiveData<Integer> getNumberOfTotalBeds();
}

package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.relations.BedAndPatient;

import java.util.List;

@Dao
public interface BedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBed(Bed bed);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBed(Bed bed);

    @Delete
    void deleteBed(Bed bed);

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

    @Query("SELECT name FROM patient_table p, bed_table b WHERE p.bed_number = b.number")
    LiveData<String> getPatientNameFromBed();

    @Transaction
    @Query("SELECT * FROM bed_table left join patient_table on patient_table.bed_number = bed_table.number")
    LiveData<List<BedAndPatient>> getAllBedAndPatient();
}

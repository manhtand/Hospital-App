package com.example.krankenhaus.srccode.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import io.reactivex.Completable;

import com.example.krankenhaus.srccode.entities.Medication;

@Dao
public interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedication(Medication medication);
}

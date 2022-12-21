package com.example.krankenhaus.srccode.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.krankenhaus.srccode.entities.BloodTest;

import io.reactivex.Completable;

@Dao
public interface BloodTestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBloodTest(BloodTest bloodTest);

    @Delete
    void deleteBloodTest(BloodTest bloodTest);
}

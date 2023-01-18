package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.relations.BloodTestAndRecord;

import java.util.List;

@Dao
public interface BloodTestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBloodTest(BloodTest bloodTest);

    @Delete
    void deleteBloodTest(BloodTest bloodTest);

    @Transaction
    @Query("SELECT * FROM blood_test_table WHERE blood_test_table.processing_state = 0 ORDER BY blood_test_table.creation_date ASC")
    LiveData<List<BloodTestAndRecord>> getAllNewBloodTestAndRecord();

    @Query("SELECT * FROM blood_test_table WHERE blood_test_table.record_id = :recordid ORDER BY blood_test_table.id ASC")
    LiveData<BloodTest> getAllBloodTestByRecordID(int recordid);
}

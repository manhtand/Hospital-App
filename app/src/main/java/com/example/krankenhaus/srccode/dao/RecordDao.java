package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;

@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecord(Record record);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateRecord(Record record);

    @Query("SELECT * FROM records")
    public LiveData<List<Record>> getAllRecords();

    @Transaction
    @Query("SELECT * FROM records WHERE records.id = :recordId")
    public LiveData<RecordWithAll> getRecordWithAllByRecordId(int recordId);
}

package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.VisitAndRecord;

import java.util.List;

@Dao
public interface VisitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVisit(Visit visit);

    @Delete
    void deleteVisit(Visit visit);

    @Query("SELECT * FROM visit_table ORDER BY visit_table.id ASC")
    LiveData<List<Visit>> getAllVisits();

    @Query("SELECT * FROM visit_table WHERE visit_table.record_id = :recordID ORDER BY visit_table.id ASC")
    LiveData<List<Visit>> getAllVisitByRecordID(int recordID);

    @Transaction
    @Query("SELECT * FROM visit_table ORDER BY visit_table.creation_date ASC")
    LiveData<List<VisitAndRecord>> getAllVisitAndRecord();
}

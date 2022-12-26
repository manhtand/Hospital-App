package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;

@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(Record record);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecord(Record record);

    @Query("SELECT * FROM record_table")
    LiveData<List<Record>> getAllRecords();

    @Transaction
    @Query("SELECT * FROM record_table WHERE record_table.id = :recordId")
    LiveData<List<RecordWithAll>> getRecordWithAllByRecordId(int recordId);

    @Transaction
    @Query("SELECT * FROM record_table WHERE record_table.patient_insurance_number = :insuranceNumber")
    LiveData<RecordAndVisitAndPatient> getRecordAndPatientAndVisitByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM record_table")
    LiveData<List<RecordAndVisitAndPatient>> getAllRecordAndPatientAndVisit();
}

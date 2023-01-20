package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.relations.RecordAndBloodTestAndMRI;
import com.example.krankenhaus.srccode.entities.relations.RecordAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;
import com.example.krankenhaus.srccode.entities.relations.RecordWithAll;

@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecord(Record record);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecord(Record record);

    @Delete
    void deleteRecord(Record record);

    @Query("SELECT * FROM record_table")
    LiveData<List<Record>> getAllRecords();

    @Transaction
    @Query("SELECT * FROM record_table WHERE record_table.id = :recordId")
    LiveData<List<RecordWithAll>> getRecordWithAllByRecordId(int recordId);

    @Transaction
    @Query("SELECT * FROM record_table WHERE record_table.patient_insurance_number = :insuranceNumber")
    LiveData<RecordWithAll> getRecordWithAllByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM record_table WHERE record_table.patient_insurance_number = :insuranceNumber")
    LiveData<RecordAndVisitAndPatient> getRecordAndPatientAndVisitByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM record_table")
    LiveData<List<RecordAndVisitAndPatient>> getAllRecordAndPatientAndVisit();

    @Transaction
    @Query("SELECT * FROM (SELECT * FROM record_table r LEFT JOIN blood_test_table b ON b.record_id = r.id LEFT JOIN mri_table m ON r.id = m.record_id ) WHERE patient_insurance_number = :insuranceNumber")
    LiveData<RecordAndBloodTestAndMRI> getAllRecordAndBloodTestAndMRIByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM record_table r JOIN patient_table p ON r.patient_insurance_number = p.insurance_number WHERE r.id = :recordID")
    LiveData<RecordAndPatient> getRecordAndPatientByRecordID(int recordID);
}

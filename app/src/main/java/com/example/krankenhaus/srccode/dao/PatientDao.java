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

import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndBed;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;
import com.example.krankenhaus.srccode.entities.relations.RecordAndVisitAndPatient;

@Dao
public interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPatient(Patient patient);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

    @Query("SELECT * FROM patient_table ORDER BY name ASC")
    LiveData<List<Patient>> getAllPatients();

    @Transaction
    @Query("SELECT * FROM patient_table NATURAL JOIN bed_table where patient_table.bed_number = bed_table.number ORDER BY name ASC")
    LiveData<List<PatientAndBed>> getAllPatientAndBeds();

    @Query("SELECT * FROM patient_table WHERE patient_table.insurance_number = :insuranceNumber")
    LiveData<Patient> getPatientByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM patient_table p WHERE p.insurance_number = :insuranceNumber")
    LiveData<PatientAndBed> getPatientAndBedByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM patient_table WHERE patient_table.insurance_number = :insuranceNumber")
    LiveData<PatientAndRecord> getPatientAndRecordByInsuranceNumber(String insuranceNumber);
}

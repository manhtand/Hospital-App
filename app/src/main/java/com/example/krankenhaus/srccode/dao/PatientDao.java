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

import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.relations.PatientAndRecord;

@Dao
public interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPatient(Patient patient);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePatient(Patient patient);

    @Query("SELECT * FROM patient_table ORDER BY name ASC")
    LiveData<List<Patient>> getAllPatients();

    @Query("SELECT * FROM patient_table WHERE patient_table.insurance_number = :insuranceNumber")
    LiveData<Patient> getPatientByInsuranceNumber(String insuranceNumber);

    @Transaction
    @Query("SELECT * FROM patient_table WHERE patient_table.insurance_number = :insuranceNumber")
    LiveData<PatientAndRecord> getPatientAndRecordByInsuranceNumber(String insuranceNumber);
}

package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "record_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Patient.class,
                        parentColumns = {"insurance_number"},
                        childColumns = {"patient_insurance_number"},
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Record {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int recordId;

    @ColumnInfo(name = "patient_insurance_number", index = true)
    private String patientInsuranceNumber;

    @ColumnInfo(name = "medication")
    private String medication;

    public Record(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getPatientInsuranceNumber() {
        return patientInsuranceNumber;
    }

    public void setPatientInsuranceNumber(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
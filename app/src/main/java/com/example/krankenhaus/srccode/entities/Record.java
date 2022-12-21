package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "records",
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
    int RecordId;

    @ColumnInfo(name = "patient_insurance_number", index = true)
    private String patientInsuranceNumber;

    public Record(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }

    public int getRecordId() {
        return RecordId;
    }

    public void setRecordId(int recordId) {
        this.RecordId = recordId;
    }

    public String getPatientInsuranceNumber() {
        return patientInsuranceNumber;
    }

    public void setPatientInsuranceNumber(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }
}
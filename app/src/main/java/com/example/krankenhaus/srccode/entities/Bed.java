package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

@Entity(tableName = "bed_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Patient.class,
                        parentColumns = {"insurance_number"},
                        childColumns = {"patient_insurance_number"},
                        onDelete = ForeignKey.SET_NULL
                )
        }
)
public class Bed {
    @PrimaryKey
    @ColumnInfo(name = "number")
    int number;

    @ColumnInfo(name = "patient_insurance_number", index = true)
    String patientInsuranceNumber;

    public Bed(int number, @Nullable String patientInsuranceNumber) {
        this.number = number;
        this.patientInsuranceNumber = patientInsuranceNumber;
    }

    @Ignore
    public boolean isOccupied() {
        return patientInsuranceNumber != null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPatientInsuranceNumber() {
        return patientInsuranceNumber;
    }

    public void setPatientInsuranceNumber(String patientInsuranceNumber) {
        this.patientInsuranceNumber = patientInsuranceNumber;
    }
}

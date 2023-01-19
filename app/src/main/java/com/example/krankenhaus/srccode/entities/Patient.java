package com.example.krankenhaus.srccode.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

import com.example.krankenhaus.srccode.converter.LocalDateConverter;

@Entity(
        tableName = "patient_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Bed.class,
                        parentColumns = {"number"},
                        childColumns = {"bed_number"},
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Patient {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "insurance_number")
    private String insuranceNumber;

    @ColumnInfo(name = "bed_number", index = true)
    private int bedNumber;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date_of_birth")
    @TypeConverters(LocalDateConverter.class)
    private LocalDate dateOfBirth;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "place_of_residence")
    private String placeOfResidence;

    @ColumnInfo(name = "zip_code")
    private String zipCode;

    @ColumnInfo(name = "health_insurance_company")
    private String healthInsuranceCompany;

    @ColumnInfo(name = "date_of_admission")
    @TypeConverters(LocalDateConverter.class)
    private LocalDate admissionDate;

    @ColumnInfo(name = "is_discharged")
    private boolean isDischarged;

    public Patient(String insuranceNumber, int bedNumber, String name, LocalDate dateOfBirth, String address, String placeOfResidence, String zipCode, String healthInsuranceCompany, boolean isDischarged) {
        this.insuranceNumber = insuranceNumber;
        this.bedNumber = bedNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.placeOfResidence = placeOfResidence;
        this.zipCode = zipCode;
        this.healthInsuranceCompany = healthInsuranceCompany;
        this.admissionDate = LocalDate.now();
        this.isDischarged = isDischarged;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHealthInsuranceCompany() {
        return healthInsuranceCompany;
    }

    public void setHealthInsuranceCompany(String healthInsuranceCompany) {
        this.healthInsuranceCompany = healthInsuranceCompany;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public boolean isDischarged() {
        return isDischarged;
    }

    public void setDischarged(boolean discharged) {
        isDischarged = discharged;
    }
}

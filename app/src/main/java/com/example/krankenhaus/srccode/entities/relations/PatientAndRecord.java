package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;

public class PatientAndRecord {
    @Embedded
    public Patient patient;

    @Relation(
            parentColumn = "insurance_number",
            entityColumn = "patient_insurance_number"
    )
    public Record record;
}

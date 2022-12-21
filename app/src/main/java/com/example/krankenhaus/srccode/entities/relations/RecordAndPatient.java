package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;

public class RecordAndPatient {
    @Embedded
    public Record record;

    @Relation(
            parentColumn = "patient_insurance_number",
            entityColumn = "insurance_number"
    )

    public Patient patient;
}

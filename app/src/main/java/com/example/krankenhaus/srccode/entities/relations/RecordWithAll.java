package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.Patient;

public class RecordWithAll {
    @Embedded
    public Record record;

    @Relation(
            entity = Visit.class,
            parentColumn = "id",
            entityColumn = "record_id"
    )
    public List<Visit> visits;

    @Relation(
            entity = BloodTest.class,
            parentColumn = "id",
            entityColumn = "record_id"
    )
    public List<BloodTest> bloodTest;

    @Relation(
            entity = MRI.class,
            parentColumn = "id",
            entityColumn = "record_id"
    )
    public List<MRI> mri;

    @Relation(
            entity = Patient.class,
            parentColumn = "patient_insurance_number",
            entityColumn = "insurance_number"
    )
    public Patient patient;
}

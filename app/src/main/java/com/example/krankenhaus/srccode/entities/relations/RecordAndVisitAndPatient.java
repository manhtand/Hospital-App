package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;

public class RecordAndVisitAndPatient {
    @Embedded
    public Record record;

    @Relation(
            parentColumn = "patient_insurance_number",
            entityColumn = "insurance_number"
    )
    public Patient patient;

    @Relation(
            entity = Visit.class,
            parentColumn = "id",
            entityColumn = "record_id"
    )
    public List<Visit> visits;
}

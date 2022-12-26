package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.Patient;

public class PatientAndBed {
    @Embedded
    public Patient patient;

    @Relation(
            parentColumn = "bed_number",
            entityColumn = "number"
    )
    public Bed bed;
}

package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.Patient;

public class BedAndPatient {
    @Embedded
    public Bed bed;

    @Relation(
            parentColumn = "number",
            entityColumn = "bed_number"
    )

    public Patient patient;
}

package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Record;

import java.util.List;

public class RecordAndBloodTestAndMRI {
    @Embedded
    public Record record;

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
}

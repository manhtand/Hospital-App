package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Record;

public class MRIAndRecord {
    @Embedded
    public MRI mri;

    @Relation(
            entity = Record.class,
            parentColumn = "record_id",
            entityColumn = "id"
    )
    public Record record;
}

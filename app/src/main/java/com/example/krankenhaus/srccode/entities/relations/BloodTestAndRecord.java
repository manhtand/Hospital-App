package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.Record;

public class BloodTestAndRecord {
    @Embedded
    public BloodTest bloodTest;

    @Relation(
            entity = Record.class,
            parentColumn = "record_id",
            entityColumn = "id"
    )
    public Record record;
}

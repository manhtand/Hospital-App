package com.example.krankenhaus.srccode.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;

public class VisitAndRecord {
    @Embedded
    public Visit visit;

    @Relation(
            parentColumn = "record_id",
            entityColumn = "id"
    )

    public Record record;
}

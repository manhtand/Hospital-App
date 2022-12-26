package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

import com.example.krankenhaus.srccode.converter.LocalDataTimeConverter;

@Entity(
        tableName = "visit_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Record.class,
                        parentColumns = {"id"},
                        childColumns = {"record_id"},
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Visit {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int visitId;

    @ColumnInfo(name = "record_id", index = true)
    private int recordId;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "creation_date")
    @TypeConverters(LocalDataTimeConverter.class)
    private LocalDateTime creationDate;

    public Visit(int recordId, String description) {
        this.recordId = recordId;
        this.description = description;
        this.creationDate = LocalDateTime.now();
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

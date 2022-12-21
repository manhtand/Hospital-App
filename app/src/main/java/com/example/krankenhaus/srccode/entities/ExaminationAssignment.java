package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

import com.example.krankenhaus.srccode.converter.LocalDataTimeConverter;

@Entity(
        tableName = "examination_assignment_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Record.class,
                        parentColumns = {"id"},
                        childColumns = {"record_id"},
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class ExaminationAssignment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "record_id", index = true)
    private int recordId;

    @ColumnInfo(name = "creation_date")
    @TypeConverters(LocalDataTimeConverter.class)
    private LocalDateTime creationTimestamp;

    @ColumnInfo(name = "examination_type")
    private String examinationType;

    public ExaminationAssignment(int id, int recordId, String examinationType) {
        this.id = id;
        this.recordId = recordId;
        this.creationTimestamp = LocalDateTime.now();
        this.examinationType = examinationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        examinationType = examinationType;
    }
}

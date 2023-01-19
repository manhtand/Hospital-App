package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

import com.example.krankenhaus.srccode.converter.LocalDataTimeConverter;

public class Examination {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "record_id", index = true)
    private int recordId;

    @ColumnInfo(name = "processing_state")
    private Boolean processingState; //false = in progress; true = progressed

    @ColumnInfo(name = "creation_date")
    @TypeConverters(LocalDataTimeConverter.class)
    private LocalDateTime creationTimestamp;

    @ColumnInfo(name = "execution_date")
    @TypeConverters(LocalDataTimeConverter.class)
    private LocalDateTime executionTimestamp;

    /*public Examination(int recordId) {
        this.recordId = recordId;
        this.processingState = false;
        this.creationTimestamp = LocalDateTime.now();
        this.executionTimestamp = LocalDateTime.of(0001,01,1,0,0);
    }*/

    public Examination(int recordId, boolean processingState, LocalDateTime executionTimestamp) {
        this.recordId = recordId;
        this.processingState = processingState;
        this.creationTimestamp = LocalDateTime.now();
        this.executionTimestamp = executionTimestamp;
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

    public Boolean getProcessingState() {
        return processingState;
    }

    public void setProcessingState(Boolean processingState) {
        this.processingState = processingState;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public void setExecutionTimestamp(LocalDateTime executionTimestamp) {
        this.executionTimestamp = executionTimestamp;
    }
}
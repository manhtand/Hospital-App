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

    @ColumnInfo(name = "execution_date")
    @TypeConverters(LocalDataTimeConverter.class)
    private LocalDateTime executionTimestamp;

    public Examination(int id, int recordId) {
        this.id = id;
        this.recordId = recordId;
        this.executionTimestamp = LocalDateTime.now();
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

    public LocalDateTime getExecutionTimestamp() {
        return executionTimestamp;
    }

    public void setExecutionTimestamp(LocalDateTime executionTimestamp) {
        this.executionTimestamp = executionTimestamp;
    }
}

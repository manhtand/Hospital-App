package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "medication_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Record.class,
                        parentColumns = {"id"},
                        childColumns = {"record_id"},
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Medication {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "record_id", index = true)
    private int recordId;

    @ColumnInfo(name = "name")
    private String name;

    public Medication(int recordId, String name) {
        this.recordId = recordId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

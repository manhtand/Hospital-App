package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.time.LocalDateTime;

@Entity(
        tableName = "mri_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Record.class,
                        parentColumns = {"id"},
                        childColumns = {"record_id"},
                        onDelete = ForeignKey.CASCADE
                )
        },
        inheritSuperIndices = true
)
public class MRI extends Examination{
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    /*public MRI(int recordId, byte[] image) {
        super(recordId);

        this.image = image;
    }

    @Ignore*/
    public MRI(int recordId, boolean processingState, LocalDateTime executionTimestamp, byte[] image) {
        super(recordId, processingState, executionTimestamp);

        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

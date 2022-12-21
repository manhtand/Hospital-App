package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "mrt_table",
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
public class MRT extends Examination{
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public MRT(int id, int recordId, byte[] image) {
        super(id, recordId);

        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

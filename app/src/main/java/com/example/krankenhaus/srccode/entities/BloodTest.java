package com.example.krankenhaus.srccode.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.time.LocalDateTime;

@Entity(
        tableName = "blood_test_table",
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
public class BloodTest extends Examination{
    @ColumnInfo(name = "leukocytes_per_nano_liter")
    private double leukocytesPerNanoLiter;

    @ColumnInfo(name = "lymphocytes_in_hundred_per_nano_liter")
    private double lymphocytesInHundredPerNanoLiter;

    @ColumnInfo(name = "lymphocytes_leukocytes_ratio")
    private double lymphocytesLeukocytesRatio;

    /*public BloodTest( int recordId, double leukocytesPerNanoLiter, double lymphocytesInHundredPerNanoLiter, double lymphocytesLeukocytesRatio) {
        super(recordId);
        this.leukocytesPerNanoLiter = leukocytesPerNanoLiter;
        this.lymphocytesInHundredPerNanoLiter = lymphocytesInHundredPerNanoLiter;
        this.lymphocytesLeukocytesRatio = lymphocytesLeukocytesRatio;
    }

    @Ignore*/
    public BloodTest(int recordId, boolean processingState, LocalDateTime executionTimestamp, double leukocytesPerNanoLiter, double lymphocytesInHundredPerNanoLiter, double lymphocytesLeukocytesRatio) {
        super(recordId, processingState, executionTimestamp);
        this.leukocytesPerNanoLiter = leukocytesPerNanoLiter;
        this.lymphocytesInHundredPerNanoLiter = lymphocytesInHundredPerNanoLiter;
        this.lymphocytesLeukocytesRatio = lymphocytesLeukocytesRatio;
    }

    public double getLeukocytesPerNanoLiter() {
        return leukocytesPerNanoLiter;
    }
    public void setLeukocytesPerNanoLiter(double leukocytesPerNanoLiter) {
        this.leukocytesPerNanoLiter = leukocytesPerNanoLiter;
    }

    public double getLymphocytesInHundredPerNanoLiter() {
        return lymphocytesInHundredPerNanoLiter;
    }
    public void setLymphocytesInHundredPerNanoLiter(double lymphocytesInHundredPerNanoLiter) {
        this.lymphocytesInHundredPerNanoLiter = lymphocytesInHundredPerNanoLiter;
    }

    public double getLymphocytesLeukocytesRatio() {
        return lymphocytesLeukocytesRatio;
    }
    public void setLymphocytesLeukocytesRatio(double lymphocytesLeukocytesRatio) {
        this.lymphocytesLeukocytesRatio = lymphocytesLeukocytesRatio;
    }
}

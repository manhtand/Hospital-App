package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.example.krankenhaus.srccode.entities.ExaminationAssignment;

@Dao
public interface ExaminationAssignmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertExaminationAssignment(ExaminationAssignment examinationAssignment);

    @Delete
    public void deleteExaminationAssignment(ExaminationAssignment examinationAssignment);

    @Query("SELECT * FROM examination_assignment_table")
    public LiveData<List<ExaminationAssignment>> getAllExaminationAssignments();
}

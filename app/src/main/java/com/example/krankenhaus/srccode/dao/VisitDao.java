package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.krankenhaus.srccode.entities.Visit;

import java.util.List;

@Dao
public interface VisitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVisit(Visit visit);

    @Query("SELECT * FROM visit_table ORDER BY visit_table.id ASC")
    LiveData<List<Visit>> getAllVisits();
}

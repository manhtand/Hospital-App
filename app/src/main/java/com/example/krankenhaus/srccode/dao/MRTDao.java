package com.example.krankenhaus.srccode.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.krankenhaus.srccode.entities.MRT;

@Dao
public interface MRTDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMRT(MRT mrt);
}

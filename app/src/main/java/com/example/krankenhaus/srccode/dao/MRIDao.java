package com.example.krankenhaus.srccode.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.krankenhaus.srccode.entities.MRI;

@Dao
public interface MRIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMRT(MRI mri);
}

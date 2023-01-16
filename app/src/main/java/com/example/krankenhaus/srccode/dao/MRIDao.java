package com.example.krankenhaus.srccode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.krankenhaus.srccode.entities.MRI;

@Dao
public interface MRIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMRT(MRI mri);

    @Delete
    void deleteMRI(MRI mri);

    @Query("SELECT * FROM mri_table WHERE mri_table.record_id = :recordid ORDER BY mri_table.id ASC" )
    LiveData<MRI> getAllMRIByRecordID(int recordid);
}

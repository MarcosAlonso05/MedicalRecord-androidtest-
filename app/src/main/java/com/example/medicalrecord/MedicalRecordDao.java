package com.example.medicalrecord;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicalRecordDao {
    @Insert
    void insert(MedicalRecord medicalRecord);
    @Delete
    void delete(MedicalRecord medicalRecord);
    @Query("DELETE FROM medical_record_table")
    void deleteAll();
    @Query("SELECT * FROM medical_record_table ORDER BY name ASC")
    LiveData<List<MedicalRecord>> getAllRecords();
}

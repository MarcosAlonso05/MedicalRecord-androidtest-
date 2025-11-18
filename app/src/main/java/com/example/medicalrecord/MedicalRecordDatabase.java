package com.example.medicalrecord;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MedicalRecord.class}, version = 1)
public abstract class MedicalRecordDatabase extends RoomDatabase {
    private static MedicalRecordDatabase instance;
    public abstract MedicalRecordDao medicalRecordDao();
    public static synchronized MedicalRecordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MedicalRecordDatabase.class, "medical_record_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

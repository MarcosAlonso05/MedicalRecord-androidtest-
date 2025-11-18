package com.example.medicalrecord;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicalRecordRepository {
    private MedicalRecordDao medicalRecordDao;
    private LiveData<List<MedicalRecord>> allRecords;
    private final ExecutorService executorService;
    public MedicalRecordRepository(Application application) {
        MedicalRecordDatabase database = MedicalRecordDatabase.getInstance(application);
        medicalRecordDao = database.medicalRecordDao();
        allRecords = medicalRecordDao.getAllRecords();
        executorService = Executors.newFixedThreadPool(2);
    }
    public void insert(MedicalRecord medicalRecord) {
        executorService.execute(() -> medicalRecordDao.insert(medicalRecord));
    }
    public void delete(MedicalRecord medicalRecord) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            medicalRecordDao.delete(medicalRecord);
        });
    }
    public void deleteAll() {
        executorService.execute(medicalRecordDao::deleteAll);
    }
    public LiveData<List<MedicalRecord>> getAllRecords() {
        return allRecords;
    }
}

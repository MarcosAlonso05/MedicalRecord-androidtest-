package com.example.medicalrecord;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MedicalRecordViewModel extends AndroidViewModel {
    private MedicalRecordRepository repository;
    private LiveData<List<MedicalRecord>> allRecords;
    public MedicalRecordViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicalRecordRepository(application);
        allRecords = repository.getAllRecords();
    }
    public void insert(MedicalRecord medicalRecord) {
        repository.insert(medicalRecord);
    }
    public void update(MedicalRecord medicalRecord) {
        repository.update(medicalRecord);
    }
    public void delete(MedicalRecord medicalRecord) {
        repository.delete(medicalRecord);
    }
    public void deleteAll() {
        repository.deleteAll();
    }
    public LiveData<List<MedicalRecord>> getAllRecords() {
        return allRecords;
    }
}

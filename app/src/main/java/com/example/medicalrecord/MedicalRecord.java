package com.example.medicalrecord;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medical_record_table")
public class MedicalRecord implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;
    private String condition;
    public MedicalRecord(String name, int age, String condition) {
        this.name = name;
        this.age = age;
        this.condition = condition;
    }
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCondition() { return condition; }
}

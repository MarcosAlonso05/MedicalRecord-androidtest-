package com.example.medicalrecord;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RecordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_AGE = "EXTRA_AGE";
    public static final String EXTRA_CONDITION = "EXTRA_CONDITION";

    private EditText editName, editAge, editCondition;
    private MedicalRecordViewModel viewModel;
    private int recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        editName = findViewById(R.id.editDetailName);
        editAge = findViewById(R.id.editDetailAge);
        editCondition = findViewById(R.id.editDetailCondition);

        viewModel = new ViewModelProvider(this).get(MedicalRecordViewModel.class);

        recordId = getIntent().getIntExtra(EXTRA_ID, -1);

        editName.setText(getIntent().getStringExtra(EXTRA_NAME));
        editAge.setText(String.valueOf(getIntent().getIntExtra(EXTRA_AGE, 0)));
        editCondition.setText(getIntent().getStringExtra(EXTRA_CONDITION));

        Button buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        buttonSaveChanges.setOnClickListener(v -> saveChanges());

        FloatingActionButton fabBack = findViewById(R.id.fab_back);
        fabBack.setOnClickListener(v -> finish());
    }

    private void saveChanges() {
        String name = editName.getText().toString();
        int age = Integer.parseInt(editAge.getText().toString());
        String condition = editCondition.getText().toString();

        MedicalRecord updated = new MedicalRecord(name, age, condition);
        updated.setId(recordId);

        viewModel.update(updated);

        Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show();
        finish();
    }
}

package com.example.medicalrecord;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextCondition;
    private Button buttonSave;
    private Button buttonDelete;
    private RecyclerView recyclerView;
    private MedicalRecordViewModel medicalRecordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextCondition = findViewById(R.id.editTextCondition);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final MedicalRecordAdapter adapter = new MedicalRecordAdapter();
        recyclerView.setAdapter(adapter);
        medicalRecordViewModel = new ViewModelProvider(this).get(MedicalRecordViewModel.class);
        medicalRecordViewModel.getAllRecords().observe(this, new Observer<List<MedicalRecord>>() {
            @Override
            public void onChanged(List<MedicalRecord> medicalRecords) {
                adapter.setRecords(medicalRecords);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // No necesitamos implementar el movimiento (drag and drop), así que retornamos false
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Esta función se llama cuando un elemento es deslizado (swipe)
                // Obtenemos la posición del elemento deslizado
                int position = viewHolder.getAdapterPosition();
                // Le pedimos al ViewModel que borre el registro en esa posición
                medicalRecordViewModel.delete(adapter.getRecordAt(position));

                // Opcional: Mostrar un mensaje de confirmación o "deshacer"
                Toast.makeText(MainActivity.this, "Registro médico eliminado", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView); // ¡No olvides adjuntarlo a tu RecyclerView!

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllRecords();
            }
        });
    }

    private void saveRecord() {
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String condition = editTextCondition.getText().toString();
        if (name.isEmpty() || age.isEmpty() || condition.isEmpty()) {
            Toast.makeText(this, "Por favor, completeeeee todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            MedicalRecord record = new MedicalRecord(name, Integer.parseInt(age), condition);
            medicalRecordViewModel.insert(record);
            Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteAllRecords() {
        medicalRecordViewModel.deleteAll();
        Toast.makeText(this, "Todos los registros eliminados", Toast.LENGTH_SHORT).show();
    }
}
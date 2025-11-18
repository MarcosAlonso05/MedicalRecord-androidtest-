package com.example.medicalrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecordDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.medicalrecord.EXTRA_NAME";
    public static final String EXTRA_AGE = "com.example.medicalrecord.EXTRA_AGE";
    public static final String EXTRA_CONDITION = "com.example.medicalrecord.EXTRA_CONDITION";

    private TextView textViewName, textViewAge, textViewCondition;
    private Button buttonEdit;
    private FloatingActionButton fabBack;
    private LinearLayout contentLayout;

    private boolean isEditMode = false;
    private EditText editTextName, editTextAge, editTextCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        // Inicializar las vistas
        textViewName = findViewById(R.id.textViewDetailName);
        textViewAge = findViewById(R.id.textViewDetailAge);
        textViewCondition = findViewById(R.id.textViewDetailCondition);
        fabBack = findViewById(R.id.fab_back);
        buttonEdit = findViewById(R.id.button_edit); // El nuevo botón de editar
        contentLayout = findViewById(R.id.content_layout); // El LinearLayout que contiene los TextViews

        // Obtener datos del Intent
        String name = getIntent().getStringExtra(EXTRA_NAME);
        int age = getIntent().getIntExtra(EXTRA_AGE, 0);
        String condition = getIntent().getStringExtra(EXTRA_CONDITION);

        // Establecer los datos iniciales
        textViewName.setText(name);
        textViewAge.setText("Edad: " + age);
        textViewCondition.setText("Condición: " + condition);

        // Configurar el listener para el botón de volver
        fabBack.setOnClickListener(view -> {
            // Si estamos en modo de edición, preguntamos antes de salir o simplemente cancelamos la edición
            if (isEditMode) {
                // Opción 1: Salir del modo edición sin guardar
                toggleEditMode();
            } else {
                // Opción 2: Salir de la actividad
                finish();
            }
        });

        // Configurar el listener para el nuevo botón de editar/guardar
        buttonEdit.setOnClickListener(view -> {
            toggleEditMode();
        });
    }

    private void toggleEditMode() {
        isEditMode = !isEditMode; // Invertir el estado

        if (isEditMode) {
            // --- ENTRAR EN MODO EDICIÓN ---
            buttonEdit.setText("Guardar");

            // Ocultar los TextViews
            textViewName.setVisibility(View.GONE);
            textViewAge.setVisibility(View.GONE);
            textViewCondition.setVisibility(View.GONE);

            // Crear y configurar los EditTexts
            editTextName = new EditText(this);
            editTextName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            editTextName.setText(textViewName.getText());
            editTextName.setHint("Nombre del Paciente");

            editTextAge = new EditText(this);
            editTextAge.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // Extraemos solo el número de la edad
            String ageString = textViewAge.getText().toString().replace("Edad: ", "");
            editTextAge.setText(ageString);
            editTextAge.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            editTextAge.setHint("Edad");

            editTextCondition = new EditText(this);
            editTextCondition.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // Extraemos solo la condición
            String conditionString = textViewCondition.getText().toString().replace("Condición: ", "");
            editTextCondition.setText(conditionString);
            editTextCondition.setHint("Condición");

            // Añadir los EditTexts al layout (en la posición de los TextViews)
            contentLayout.addView(editTextName, 0);
            contentLayout.addView(editTextAge, 1);
            contentLayout.addView(editTextCondition, 2);

        } else {
            // --- SALIR DEL MODO EDICIÓN (GUARDAR) ---
            buttonEdit.setText("Editar");

            // Obtener los nuevos valores de los EditTexts
            String newName = editTextName.getText().toString();
            int newAge = Integer.parseInt(editTextAge.getText().toString());
            String newCondition = editTextCondition.getText().toString();

            // Actualizar los TextViews con los nuevos datos
            textViewName.setText(newName);
            textViewAge.setText("Edad: " + newAge);
            textViewCondition.setText("Condición: " + newCondition);

            // Quitar los EditTexts del layout
            contentLayout.removeView(editTextName);
            contentLayout.removeView(editTextAge);
            contentLayout.removeView(editTextCondition);

            // Mostrar los TextViews de nuevo
            textViewName.setVisibility(View.VISIBLE);
            textViewAge.setVisibility(View.VISIBLE);
            textViewCondition.setVisibility(View.VISIBLE);

            // --- IMPORTANTE: Aquí deberías añadir la lógica para guardar los datos en la base de datos ---
            // Por ejemplo, crear un nuevo Intent, poner los datos actualizados como extras
            // y devolverlo a MainActivity usando setResult().
            Intent resultIntent = new Intent();
            // resultIntent.putExtra(EXTRA_ID, id); // Necesitarás pasar un ID para saber qué registro actualizar
            resultIntent.putExtra(EXTRA_NAME, newName);
            resultIntent.putExtra(EXTRA_AGE, newAge);
            resultIntent.putExtra(EXTRA_CONDITION, newCondition);
            setResult(RESULT_OK, resultIntent);
            // Si no quieres cerrar la actividad al guardar, simplemente quita el finish().
            // finish();
        }
    }
}

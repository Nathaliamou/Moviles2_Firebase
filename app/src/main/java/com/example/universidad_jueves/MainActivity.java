package com.example.universidad_jueves;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etcarnet, etnombre, etcarrera, etsemestre;
    Button btbuscar, btadicionar, btmodificar, btcancelar, bteliminar, btanular;

    CheckBox cbactivo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //definir variables
    String carnet, nombre, carrera, semestre, id_documento, colleccion = "Estudiante";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asociar obj java con xml
        btadicionar = findViewById(R.id.btadicionar);
        btanular = findViewById(R.id.btanular);
        btbuscar = findViewById(R.id.btbuscar);
        btcancelar = findViewById(R.id.btcancelar);
        bteliminar = findViewById(R.id.bteliminar);
        btmodificar = findViewById(R.id.btmodificar);
        cbactivo = findViewById(R.id.cbactivo);
        etcarnet = findViewById(R.id.etcarnet);
        etcarrera = findViewById(R.id.etcarrera);
        etnombre = findViewById(R.id.etnombre);
        etsemestre = findViewById(R.id.etsemestre);
        etcarnet.requestFocus();
    } //fin metodo onCreate

    public void Adicionar(View view) {

        carnet = etcarnet.getText().toString();
        nombre = etnombre.getText().toString();
        carrera = etcarrera.getText().toString();
        semestre = etsemestre.getText().toString();
        if (!carnet.isEmpty() && !nombre.isEmpty() && !carrera.isEmpty() && !semestre.isEmpty()) {
// Create a new student with a name
            Map<String, Object> alumno = new HashMap<>();
            alumno.put("carnet", carnet);
            alumno.put("Nombre", nombre);
            alumno.put("Carrera", carrera);
            alumno.put("Semestre", semestre);
            alumno.put("Activo", "si");

// Add a new document with a generated ID
            db.collection(colleccion)
                    .add(alumno)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(MainActivity.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Log.w(TAG, "Error adding document", e);
                        }
                    });
        } else {

            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            etcarnet.requestFocus();
        }
    }//fin metodo adicionar
}

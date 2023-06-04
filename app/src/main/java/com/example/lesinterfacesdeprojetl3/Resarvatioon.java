package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Resarvatioon extends AppCompatActivity {
    private EditText nomEditText,dateEdittext,timeEdittext,matriculEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resarvatioon);
        String nameprking = getIntent().getStringExtra("Name");
        Button rsrv = findViewById(R.id.btn_submit);
        rsrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomEditText = findViewById(R.id.et_name);
                dateEdittext = findViewById(R.id.et_date);
                timeEdittext = findViewById(R.id.et_time);
                matriculEditText = findViewById(R.id.vcl);



                String nom = nomEditText.getText().toString();
                String matricule = matriculEditText.getText().toString();

                String date = dateEdittext.getText().toString();
                String time = timeEdittext.getText().toString();



                if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(date) || TextUtils.isEmpty(date) || TextUtils.isEmpty(matricule)) {
                    Toast.makeText(Resarvatioon.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isDigitsOnly(matricule)) {
                    Toast.makeText(Resarvatioon.this, "Le matricule doit être un nombre", Toast.LENGTH_SHORT).show();
                } else
                {

                    // Créer un nouvel objet HashMap ou une classe modèle pour stocker les informations de réservation
                    Map<String, Object> reservation = new HashMap<>();
                    reservation.put("nomeparking",nameprking);
                    reservation.put("nom", nom);
                    reservation.put("matricule", matricule);
                    reservation.put("date", date);
                    reservation.put("time",time);

                    // Ajouter les informations de réservation à Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DocumentReference userRef = db.collection("users").document(userId);


                    userRef.collection("reservations")
                            .add(reservation)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(Resarvatioon.this, "Réservation effectuée avec succès", Toast.LENGTH_SHORT).show();
                                // Réinitialiser les champs de texte après la réservation réussie
                                nomEditText.setText("");
                                matriculEditText.setText("");
                                dateEdittext.setText("");
                                timeEdittext.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Resarvatioon.this, "Erreur lors de la réservation", Toast.LENGTH_SHORT).show();
                            });
                }



            }
        });




    }
}
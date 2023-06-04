package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class Newparking extends AppCompatActivity {
    private EditText nameEditText, wilayaEditText, placesEditText, tarifEditText, latEditText, longEditText, ouvertureEditText, fermetureEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newparking);
        Button saveButton = findViewById(R.id.add);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nameEditText = findViewById(R.id.addname);
                wilayaEditText = findViewById(R.id.addwilaya);
                placesEditText = findViewById(R.id.addplace);
                tarifEditText = findViewById(R.id.addtarife);
                latEditText = findViewById(R.id.lignt);
                longEditText = findViewById(R.id.longt);
                ouvertureEditText = findViewById(R.id.ouverture);
                fermetureEditText = findViewById(R.id.fermeture);

                String name = nameEditText.getText().toString();
                String wilaya = wilayaEditText.getText().toString();
                String placesStr = placesEditText.getText().toString();
                String tarifStr = tarifEditText.getText().toString();
                double latStr = Double.parseDouble(latEditText.getText().toString());
                double longStr = Double.parseDouble(longEditText.getText().toString());
                String ouverture = ouvertureEditText.getText().toString();
                String fermeture = fermetureEditText.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(wilaya) || TextUtils.isEmpty(placesStr) ||
                        TextUtils.isEmpty(tarifStr) ||
                        TextUtils.isEmpty(ouverture) || TextUtils.isEmpty(fermeture)) {
                    Toast.makeText(Newparking.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    int places = Integer.parseInt(placesStr);
                    int tarif = Integer.parseInt(tarifStr);
                    double lat = Double.parseDouble(String.valueOf(latStr));
                    double lng = Double.parseDouble(String.valueOf(longStr));

                    // Create a new HashMap or model class object to store the parking information
                    Map<String, Object> parking = new HashMap<>();
                    parking.put("Name", name);
                    parking.put("Wilaya", wilaya);
                    parking.put("Nombre de places", places);
                    parking.put("Tarif", tarif);
                    parking.put("Localisation", new GeoPoint(lat, lng));
                    parking.put("Hraire d'ouverture", ouverture);
                    parking.put("Heure de ferneture", fermeture);
                    parking.put("Director", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    // Add the parking information to Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference parkings = db.collection("parkings");
                    parkings.document(name)
                            .set(parking)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(Newparking.this, "Parking ajouté avec succès", Toast.LENGTH_SHORT).show();
                                // Reset the input fields after successful addition
                                nameEditText.setText("");
                                wilayaEditText.setText("");
                                placesEditText.setText("");
                                tarifEditText.setText("");
                                latEditText.setText("");
                                longEditText.setText("");
                                ouvertureEditText.setText("");
                                fermetureEditText.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Newparking.this, "Erreur lors de l'ajout du parking", Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });



            }
        }
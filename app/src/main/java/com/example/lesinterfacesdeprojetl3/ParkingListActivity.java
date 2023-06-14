package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class ParkingListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> parkingNames;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        listView = findViewById(R.id.listViewparking);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedParkingName = parkingNames.get(position);
            // Retrieve the complete parking information based on the selected name
            retrieveParkingDetails(selectedParkingName);
        });

        parkingNames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, parkingNames);
        listView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("parkings")
                .whereEqualTo("Director", userId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        String parkingName = document.getString("Name");
                        parkingNames.add(parkingName);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }

    private void retrieveParkingDetails(String parkingName) {
        db.collection("parkings")
                .whereEqualTo("Name", parkingName)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                        // Retrieve the parking details from the document snapshot
                        String wilaya = document.getString("Wilaya");
                        int numberOfPlaces = document.getLong("Nombre de places").intValue();
                        int tarif = document.getLong("Tarif").intValue();
                        GeoPoint localisation  = document.getGeoPoint("Localisation");
                        double lat = localisation.getLatitude();
                        double lng = localisation.getLongitude();
                        String ot = document.getString("Hraire d'ouverture");
                        String ft = document.getString("Heure de ferneture");
                        // ... Retrieve other details as needed

                        // Start a new activity to display the parking details
                        Intent intent = new Intent(ParkingListActivity.this, ParkingDetailsActivity.class);
                        intent.putExtra("parkingName", parkingName);
                        intent.putExtra("wilaya", wilaya);
                        intent.putExtra("numberOfPlaces", numberOfPlaces);
                        intent.putExtra("tarif", tarif);
                        intent.putExtra("lat",lat);
                        intent.putExtra("lng",lng);
                        intent.putExtra("ot",ot);
                        intent.putExtra("ft",ft);

                        // ... Pass other details as extras

                        startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }
}

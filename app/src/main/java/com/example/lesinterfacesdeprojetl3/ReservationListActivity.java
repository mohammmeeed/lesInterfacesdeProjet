package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ReservationListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> reservationNames;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        listView = findViewById(R.id.listView);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedReservationName = reservationNames.get(position);
            // Retrieve the complete reservation information based on the selected name
            retrieveReservationDetails(selectedReservationName);
        });

        reservationNames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reservationNames);
        listView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("users").document(userId).collection("reservations")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        String reservationName = document.getString("nomeparking");
                        reservationNames.add(reservationName);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });




    }




    private void retrieveReservationDetails(String reservationName) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("users").document(userId).collection("reservations")
                .whereEqualTo("nomeparking", reservationName)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                        // Retrieve the reservation details from the document snapshot
                        String reservationMatricule = document.getString("matricule");
                        String reservationDate = document.getString("date");
                        String reservationTime = document.getString("time");
                        String reservationname = document.getString("nom");



                        // Start a new activity to display the reservation details
                        Intent intent = new Intent(ReservationListActivity.this, ReservationDetailsActivity.class);
                        intent.putExtra("reservationName", reservationName);
                        intent.putExtra("reservationMatricule", reservationMatricule);
                        intent.putExtra("reservationDate", reservationDate);
                        intent.putExtra("reservationname", reservationname);
                        intent.putExtra("reservationTime", reservationTime);



                        startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }



}
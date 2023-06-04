package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReservationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        // Retrieve the reservation details from the intent
        String reservationNameparking = getIntent().getStringExtra("reservationName");
        String reservationMatricule = getIntent().getStringExtra("reservationMatricule");
        String reservationDate = getIntent().getStringExtra("reservationDate");
        String reservationTime = getIntent().getStringExtra("reservationTime");
        String reservationname = getIntent().getStringExtra("reservationname");

        // Display the reservation details in the activity UI
        TextView nameparkingTextView = findViewById(R.id.nameprTextView);
        TextView matriculeTextView = findViewById(R.id.matriculeTextView);
        TextView dateTextView = findViewById(R.id.dateTextView);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView nameTextView = findViewById(R.id.nameTextView);


        nameparkingTextView.setText(reservationNameparking);
        matriculeTextView.setText(reservationMatricule);
        dateTextView.setText(reservationDate);
        timeTextView.setText(reservationTime);
        nameTextView.setText(reservationname);
    }
}
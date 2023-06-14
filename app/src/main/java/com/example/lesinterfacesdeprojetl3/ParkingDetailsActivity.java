package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParkingDetailsActivity extends AppCompatActivity {
    private TextView parkingNameTextView;
    private TextView parkingWilayaTextView;
    private TextView parkingTimeOpenTextView;
    private TextView parkingTimeCloseTextView;
    private TextView parkingNbrPlaceTextView;
    private TextView parkingTarifHourTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);


        parkingNameTextView = findViewById(R.id.parkingName);
        parkingWilayaTextView = findViewById(R.id.parkingWilayaPName);
        parkingTimeOpenTextView = findViewById(R.id.parkingPTimeOpen);
        parkingTimeCloseTextView = findViewById(R.id.parkingPTimeClose);
        parkingNbrPlaceTextView = findViewById(R.id.parkingPNbrPlace);
        parkingTarifHourTextView = findViewById(R.id.parkingPTarifH);
        Button location = findViewById(R.id.locationparking);
        Button modifier = findViewById(R.id.modifierparking);
        Intent intent = getIntent();
        if (intent != null) {
            String parkingName = intent.getStringExtra("parkingName");
            String parkingWilaya = intent.getStringExtra("wilaya");
            String parkingTimeOpen = intent.getStringExtra("ot");
            String parkingTimeClose = intent.getStringExtra("ft");
            int parkingNbrPlace = intent.getIntExtra("numberOfPlaces", 0);
            int parkingTarifHour = intent.getIntExtra("tarif", 0);

            parkingNameTextView.setText(parkingName);
            parkingWilayaTextView.setText(parkingWilaya);
            parkingTimeOpenTextView.setText(parkingTimeOpen);
            parkingTimeCloseTextView.setText(parkingTimeClose);
            parkingNbrPlaceTextView.setText(String.valueOf(parkingNbrPlace));
            parkingTarifHourTextView.setText(String.valueOf(parkingTarifHour));
        }



        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double lat = intent.getDoubleExtra("lat",0.0);
                double lng = intent.getDoubleExtra("lng",0.0);
                Intent lc = new Intent(ParkingDetailsActivity.this , map2.class);
                lc.putExtra("lat",lat);
                lc.putExtra("lng",lng);
            startActivity(lc);
            }
        });


        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parkingName = intent.getStringExtra("parkingName");
                String parkingTimeOpen = intent.getStringExtra("ot");
                String parkingTimeClose = intent.getStringExtra("ft");
                int parkingNbrPlace = intent.getIntExtra("numberOfPlaces", 0);
                int parkingTarifHour = intent.getIntExtra("tarif", 0);
                Intent md = new Intent(ParkingDetailsActivity.this,ModifieeParking.class);
                md.putExtra("parkingName",parkingName);
                md.putExtra("parkingTimeOpen",parkingTimeOpen);
                md.putExtra("parkingTimeClose",parkingTimeClose);
                md.putExtra("parkingNbrPlace",parkingNbrPlace);
                md.putExtra("parkingTarifHour",parkingTarifHour);
                startActivity(md);


            }
        });


    }
}
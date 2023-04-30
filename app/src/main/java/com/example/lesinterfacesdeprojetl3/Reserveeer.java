package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class Reserveeer extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserveeer);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Reserveeer.this,Resarvatioon.class);
                startActivity(i);
            }
        });




        Intent intent = getIntent();
        String nom = intent.getStringExtra("location_name");

        TextView nmprking , wlya, nmprplc ;
        nmprking = findViewById(R.id.nmprking);
        wlya = findViewById(R.id.wlya);

        nmprking.setText(nom);




    }
}
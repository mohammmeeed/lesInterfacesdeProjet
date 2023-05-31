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
TextView nametxt = findViewById(R.id.nmprking);
TextView wlyatxt = findViewById(R.id.Wilaya);
TextView nmbrplctxt = findViewById(R.id.placedispo);
TextView hdtxt = findViewById(R.id.hd);
TextView hftxt = findViewById(R.id.hf);
TextView tariftxt = findViewById(R.id.tarif);


String name = getIntent().getStringExtra("Name");
String wilaya = getIntent().getStringExtra("Wilaya");
String hd = getIntent().getStringExtra("Hraire d'ouverture");
        String hf = getIntent().getStringExtra("Heure de ferneture");
long nmbrplc = getIntent().getLongExtra("Nombre de places",0);
long tarif = getIntent().getLongExtra("Tarif",0);


nametxt.setText(name);
wlyatxt.setText(wilaya);
hdtxt.setText(hd);
hftxt.setText(hf);
tariftxt.setText(String.valueOf(tarif));
nmbrplctxt.setText(String.valueOf(nmbrplc));











        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Reserveeer.this,Resarvatioon.class);
                startActivity(i);
            }
        });







    }
}
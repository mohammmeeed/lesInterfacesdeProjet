package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Reserveeer extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserveeer);
        Button b = findViewById(R.id.button3);
TextView nametxt = findViewById(R.id.parkingName);
TextView wlyatxt = findViewById(R.id.parkingWilayaPName);
TextView nmbrplctxt = findViewById(R.id.parkingPNbrPlace);
TextView hdtxt = findViewById(R.id.parkingPTimeOpen);
TextView hftxt = findViewById(R.id.parkingPTimeClose);
TextView tariftxt = findViewById(R.id.parkingPTarifH);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


String name = getIntent().getStringExtra("Name");
String wilaya = getIntent().getStringExtra("Wilaya");
String hd = getIntent().getStringExtra("Hraire d'ouverture");
        String hf = getIntent().getStringExtra("Heure de ferneture");
long nmbrplc = getIntent().getLongExtra("Nombre de places",0);
long Tarif = getIntent().getLongExtra("Tarif",0);


nametxt.setText(name);
wlyatxt.setText(wilaya);
hdtxt.setText(hd);
hftxt.setText(hf);
tariftxt.setText(String.valueOf(Tarif));
nmbrplctxt.setText(String.valueOf(nmbrplc));











        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (currentUser != null && currentUser.isAnonymous()){
                    startActivity(new Intent(Reserveeer.this,Anonyme.class));
                }else {


                    Intent i = new Intent(Reserveeer.this, Resarvatioon.class);

                    i.putExtra("Namepark", name);
                    i.putExtra("tarifh", Tarif);


                    i.putExtra("wilaya", wilaya);


                    startActivity(i);
                }
            }
        });







    }
}
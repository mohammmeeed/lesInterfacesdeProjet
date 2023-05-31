package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;
import java.util.Map;

public class Welcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        Button b = findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcom.this,MapsActivity.class));
            }
        });



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference parkings = db.collection("parkings");
        Map<String, Object> data1 = new HashMap<>();
        data1.put("Name","centre comercial es senia");
        data1.put("Wilaya","oran");
        data1.put("Nombre de places",67);
        data1.put("Tarif",100);
        data1.put( "Localisation", new GeoPoint(35.638209, -0.589343));
        data1.put("Hraire d'ouverture","09:00");
        data1.put("Heure de ferneture","23:00");
        parkings.document("centre comercial senia").set(data1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("Name","saida");
        data2.put("Wilaya","oran");
        data2.put("Nombre de places",17);
        data2.put("Tarif",150);
        data2.put( "Localisation", new GeoPoint(35.700602, -0.647636));
        data2.put("Hraire d'ouverture","08:00");
        data2.put("Heure de ferneture","22:00");
        parkings.document("saida").set(data2);


        Map<String, Object> data3 = new HashMap<>();
        data3.put("Name","beliala");
        data3.put("Wilaya","oran");
        data3.put("Nombre de places",34);
        data3.put("Tarif",120);
        data3.put( "Localisation", new GeoPoint(35.703801, -0.640537));
        data3.put("Hraire d'ouverture","08:00");
        data3.put("Heure de ferneture","22:00");
        parkings.document("beliala").set(data3);


        Map<String, Object> data4 = new HashMap<>();
        data4.put("Name","cite prerret");
        data4.put("Wilaya","oran");
        data4.put("Nombre de places",6);
        data4.put("Tarif",50);
        data4.put( "Localisation", new GeoPoint(35.699497, -0.636296));
        data4.put("Hraire d'ouverture","08:00");
        data4.put("Heure de ferneture","23:00");
        parkings.document("cite prerret").set(data4);

        Map<String, Object> data5 = new HashMap<>();
        data5.put("Name"," Bab El Djiad");
        data5.put("Wilaya","tlmcen");
        data5.put("Nombre de places",17);
        data5.put("Tarif",100);
        data5.put( "Localisation", new GeoPoint(34.882072, -1.304849));
        data5.put("Hraire d'ouverture","08:00");
        data5.put("Heure de ferneture","20:30");
        parkings.document("cite prerret").set(data5);








    }
}
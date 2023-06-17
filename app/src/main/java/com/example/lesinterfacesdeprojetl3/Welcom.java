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
        parkings.document(" Bab El Djiad").set(data5);

        Map<String, Object> data6 = new HashMap<>();
        data6.put("Name"," tribunal said hamadine");
        data6.put("Wilaya","alger");
        data6.put("Nombre de places",19);
        data6.put("Tarif",80);
        data6.put( "Localisation", new GeoPoint(36.734032, 3.035500));
        data6.put("Hraire d'ouverture","08:00");
        data6.put("Heure de ferneture","18:30");
        parkings.document("tribunal said hamadine").set(data6);

        Map<String, Object> data7= new HashMap<>();
        data7.put("Name"," parking said hamadine");
        data7.put("Wilaya","alger");
        data7.put("Nombre de places",25);
        data7.put("Tarif",60);
        data7.put( "Localisation", new GeoPoint(36.734528, 3.031757));
        data7.put("Hraire d'ouverture","08:00");
        data7.put("Heure de ferneture","18:30");
        parkings.document("parking said hamadine").set(data7);


        Map<String, Object> data8= new HashMap<>();
        data8.put("Name"," PARKING EL MADANIA");
        data8.put("Wilaya","alger");
        data8.put("Nombre de places",71);
        data8.put("Tarif",150);
        data8.put( "Localisation", new GeoPoint(36.740506, 3.057419));
        data8.put("Hraire d'ouverture","08:00");
        data8.put("Heure de ferneture","20:45");
        parkings.document("PARKING EL MADANIA").set(data8);

        Map<String, Object> data9= new HashMap<>();
        data9.put("Name","Garage jule ferry");
        data9.put("Wilaya","oran");
        data9.put("Nombre de places",45);
        data9.put("Tarif",90);
        data9.put( "Localisation", new GeoPoint(35.687959, -0.655848));
        data9.put("Hraire d'ouverture","08:00");
        data9.put("Heure de ferneture","19:00");
        parkings.document("PARKING EL MADANIA").set(data9);

        Map<String, Object> data10= new HashMap<>();
        data10.put("Name","Parking personnel hospitalier");
        data10.put("Wilaya","oran");
        data10.put("Nombre de places",16);
        data10.put("Tarif",30);
        data10.put( "Localisation", new GeoPoint(35.670086, -0.665316));
        data10.put("Hraire d'ouverture","10:00");
        data10.put("Heure de ferneture","23:30");
        parkings.document("PARKING EL MADANIA").set(data10);

        Map<String, Object> data11= new HashMap<>();
        data11.put("Name","Parking personnel hospitalier");
        data11.put("Wilaya","oran");
        data11.put("Nombre de places",02);
        data11.put("Tarif",70);
        data11.put( "Localisation", new GeoPoint(35.655419, -0.625619));
        data11.put("Hraire d'ouverture","10:00");
        data11.put("Heure de ferneture","19:00");
        parkings.document("PARKING EL MADANIA").set(data11);

        Map<String, Object> data12= new HashMap<>();
        data12.put("Name","Parking douane");
        data12.put("Wilaya","oran");
        data12.put("Nombre de places",28);
        data12.put("Tarif",50);
        data12.put( "Localisation", new GeoPoint(35.700422, -0.621506));
        data12.put("Hraire d'ouverture","10:00");
        data12.put("Heure de ferneture","19:00");
        parkings.document("PARKING EL MADANIA").set(data12);



        Map<String,Object> data13=new HashMap<>();
        data13.put("Name","Parking KING");
        data13.put("Wilaya","Oran");
        data13.put("Nombre de places",30);
        data13.put("Tarif",100);
        data13.put("Localisation",new GeoPoint(35.66301677192824, -0.6254843952136433));
        data13.put("Hraire d'ouverture","07:00");
        data13.put("Heure de ferneture","23:00");
        parkings.document("Parking KING").set(data13);


        Map<String,Object> data14=new HashMap<>();
        data14.put("Name","Parking Auto Victor Hugo");
        data14.put("Wilaya","Oran");
        data14.put("Nombre de places",62);
        data14.put("Tarif",150);
        data14.put("Localisation",new GeoPoint(35.68644395825341, -0.6196479082035063));
        data14.put("Hraire d'ouverture","06:00");
        data14.put("Heure de ferneture","21:00");
        parkings.document("Parking Auto Victor Hugo").set(data14);



        Map<String,Object> data15=new HashMap<>();
        data15.put("Name","PARKING MALAK");
        data15.put("Wilaya","Oran");
        data15.put("Nombre de places",80);
        data15.put("Tarif",200);
        data15.put("Localisation",new GeoPoint(35.719324559979846, -0.5651736391624214));
        data15.put("Hraire d'ouverture","08:00");
        data15.put("Heure de ferneture","23:00");
        parkings.document("PARKING MALAK").set(data15);


        Map<String,Object> data16=new HashMap<>();
        data16.put("Name","Parking Mediouni");
        data16.put("Wilaya","Oran");
        data16.put("Nombre de places",30);
        data16.put("Tarif",150);
        data16.put("Localisation",new GeoPoint(35.69126594142119, -0.6427431518972395));
        data16.put("Hraire d'ouverture","08:30");
        data16.put("Heure de ferneture","23:30");
        parkings.document("Parking Mediouni").set(data16);





        Map<String,Object> data17=new HashMap<>();
        data17.put("Name","parking et lavage Village Mustapha");
        data17.put("Wilaya","jijel");
        data17.put("Nombre de places",46);
        data17.put("Tarif",150);
        data17.put("Localisation",new GeoPoint(36.84555973095268, 5.757465194063435));
        data17.put("Hraire d'ouverture","07:00");
        data17.put("Heure de ferneture","20:30");
        parkings.document("parking et lavage Village Mustapha").set(data17);//ytbdl


        Map<String,Object> data18=new HashMap<>();
        data18.put("Name","Parking Sud de l'Université");
        data18.put("Wilaya","jijel");
        data18.put("Nombre de places",108);
        data18.put("Tarif",25);
        data18.put("Localisation",new GeoPoint(36.80761832392308, 5.8377062384577645));
        data18.put("Hraire d'ouverture","07:00");
        data18.put("Heure de ferneture","23:00");
        parkings.document("Parking Sud de l'Université").set(data18);


        Map<String,Object> data19=new HashMap<>();
        data19.put("Name","Parking Naim");
        data19.put("Wilaya","jijel");
        data19.put("Nombre de places",60);
        data19.put("Tarif",150);
        data19.put("Localisation",new GeoPoint(36.8302754756078, 5.767900613052317));
        data19.put("Hraire d'ouverture","08:00");
        data19.put("Heure de ferneture","22:00");
        parkings.document("Parking Naim").set(data19);



        Map<String,Object> data20=new HashMap<>();
        data20.put("Name","Parking bab wahren Tlemcen");
        data20.put("Wilaya","Tlemcen");
        data20.put("Nombre de places",96);
        data20.put("Tarif",200);
        data20.put("Localisation",new GeoPoint(34.88794764023583, -1.3191794684820342));
        data20.put("Hraire d'ouverture","08:00");
        data20.put("Heure de ferneture","20:00");
        parkings.document("Parking bab wahren Tlemcen").set(data20);

        Map<String,Object> data21=new HashMap<>();
        data21.put("Name","Plateau Lalla Setti Parking");
        data21.put("Wilaya","Tlemcen");
        data21.put("Nombre de places",140);
        data21.put("Tarif",250);
        data21.put("Localisation",new GeoPoint(34.86824984598146, -1.314883791244053));
        data21.put("Hraire d'ouverture","07:00");
        data21.put("Heure de ferneture","23:00");
        parkings.document("Plateau Lalla Setti Parking").set(data21);

        Map<String,Object> data22=new HashMap<>();
        data22.put("Name","Parking Hôtel Novotel");
        data22.put("Wilaya","setif");
        data22.put("Nombre de places",12);
        data22.put("Tarif",40);
        data22.put("Localisation",new GeoPoint(36.193360, 5.410526));
        data22.put("Hraire d'ouverture","09:00");
        data22.put("Heure de ferneture","22:00");
        parkings.document("Parking Hôtel Novotel").set(data22);


        Map<String,Object> data23=new HashMap<>();
        data23.put("Name","Badis");
        data23.put("Wilaya","setif");
        data23.put("Nombre de places",32);
        data23.put("Tarif",70);
        data23.put("Localisation",new GeoPoint(36.178691, 5.401382));
        data23.put("Hraire d'ouverture","11:00");
        data23.put("Heure de ferneture","19:00");
        parkings.document("Badis").set(data23);


        Map<String,Object> data24=new HashMap<>();
        data24.put("Name","centre comerciel");
        data24.put("Wilaya","setif");
        data24.put("Nombre de places",9);
        data24.put("Tarif",70);
        data24.put("Localisation",new GeoPoint(36.184932, 5.391425));
        data24.put("Hraire d'ouverture","10:00");
        data24.put("Heure de ferneture","23:00");
        parkings.document("Badis").set(data24);



        Map<String,Object> data25=new HashMap<>();
        data25.put("Name","parking auto");
        data25.put("Wilaya","blida");
        data25.put("Nombre de places",38);
        data25.put("Tarif",20);
        data25.put("Localisation",new GeoPoint(36.459715, 2.694455));
        data25.put("Hraire d'ouverture","10:00");
        data25.put("Heure de ferneture","23:00");
        parkings.document("parking auto").set(data25);

        Map<String,Object> data26=new HashMap<>();
        data26.put("Name","parking sim");
        data26.put("Wilaya","blida");
        data26.put("Nombre de places",22);
        data26.put("Tarif",40);
        data26.put("Localisation",new GeoPoint(36.481664, 2.825456));
        data26.put("Hraire d'ouverture","10:00");
        data26.put("Heure de ferneture","23:00");
        parkings.document("parking sim").set(data26);



















    }
}
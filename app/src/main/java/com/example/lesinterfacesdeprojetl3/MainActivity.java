package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        final ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });



        final LinearLayout profil ;
        //hi
        profil = findViewById(R.id.l_layout1);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(MainActivity.this,profile.class);
                startActivity(intnt);
            }
        });
        final LinearLayout infos = findViewById(R.id.l_layout4);
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Infos.class);
            }
        });
        final LinearLayout info = findViewById(R.id.l_layout4);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(MainActivity.this,Infos.class);
                startActivity(info);
            }
        });
        final LinearLayout his = findViewById(R.id.l_layout2);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReservationListActivity.class));
            }
        });






        final LinearLayout infoparking = findViewById(R.id.layout_infoparking);
        infoparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ParkingListActivity.class));
            }
        });








        final LinearLayout addParkingLayout = findViewById(R.id.layout_addpark);
        addParkingLayout.setVisibility(View.GONE);
        db.collection("Users").document(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            String userType = document.getString("usertype");




                            // Update the profile image based on user type
                            if (userType != null && userType.equals("utilisateur")) {
                                addParkingLayout.setVisibility(View.GONE);
                                infoparking.setVisibility(View.GONE);
                                info.setVisibility(View.VISIBLE);
                            } else if (userType != null && userType.equals("Proprietaire")) {
                                addParkingLayout.setVisibility(View.VISIBLE);
                                infoparking.setVisibility(View.VISIBLE);
                                info.setVisibility(View.VISIBLE);


                                addParkingLayout.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(MainActivity.this,Newparking.class));
                                    }
                                });
                            }


                        }
                    }
                });


    }
}
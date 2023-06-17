package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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



        final ConstraintLayout profil ;
        //hi
        profil = findViewById(R.id.l_layout1);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(MainActivity.this,profile.class);
                startActivity(intnt);
            }
        });
        final ConstraintLayout infos = findViewById(R.id.l_layout4);
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Infos.class);
            }
        });
        final ConstraintLayout info = findViewById(R.id.l_layout4);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(MainActivity.this,Infos.class);
                startActivity(info);
            }
        });
        final ConstraintLayout his = findViewById(R.id.l_layout2);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReservationListActivity.class));
            }
        });






        final ConstraintLayout infoparking = findViewById(R.id.layout_infoparking);
        infoparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ParkingListActivity.class));
            }
        });





        ConstraintLayout help = findViewById(R.id.l_layout5);


        final ConstraintLayout addParkingLayout = findViewById(R.id.layout_addpark);
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
                                help.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(MainActivity.this,Help.class));
                                    }
                                });

                            } else if (userType != null && userType.equals("Proprietaire")) {
                                addParkingLayout.setVisibility(View.VISIBLE);
                                infoparking.setVisibility(View.VISIBLE);
                                info.setVisibility(View.VISIBLE);
                                help.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(MainActivity.this,Infospropeitaire.class));
                                    }
                                });



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
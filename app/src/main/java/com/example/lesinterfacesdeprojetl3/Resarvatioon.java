package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Resarvatioon extends AppCompatActivity {
    EditText nbrJours,nbrHeurs;
    DatePickerDialog.OnDateSetListener setListener;
    private EditText nomEditText,dateEdittext,timeEdittext,matriculEditText;
    private int mHour,mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resarvatioon);
        String nameprking = getIntent().getStringExtra("Namepark");

        Button rsrv = findViewById(R.id.btn_submit);








        nomEditText = findViewById(R.id.et_name);
        dateEdittext = findViewById(R.id.et_date);
        timeEdittext = findViewById(R.id.et_time);
        matriculEditText = findViewById(R.id.vcl);





        Calendar calendar =Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day =calendar.get(Calendar.DAY_OF_MONTH);


        dateEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Resarvatioon.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year, month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month =month+ 1;
                String datee=day+"/"+ month +"/"+year;
            }
        };


        dateEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Resarvatioon.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        m=month+1;
                        String datte=d+"/"+m+"/"+y;
                        dateEdittext.setText(datte);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        timeEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                mHour=calendar.get(Calendar.HOUR);
                mMinute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(Resarvatioon.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        timeEdittext.setText(h+":"+m);
                    }
                },mHour,mMinute,true);
                timePickerDialog.show();

            }
        });


        rsrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







                String nom = nomEditText.getText().toString();
                String matricule = matriculEditText.getText().toString();

                String date = dateEdittext.getText().toString();
                String time = timeEdittext.getText().toString();
























                if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(date) || TextUtils.isEmpty(date) || TextUtils.isEmpty(matricule)) {
                    Toast.makeText(Resarvatioon.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isDigitsOnly(matricule)) {
                    Toast.makeText(Resarvatioon.this, "Le matricule doit être un nombre", Toast.LENGTH_SHORT).show();
                } else
                {


                    // Créer un nouvel objet HashMap ou une classe modèle pour stocker les informations de réservation
                    Map<String, Object> reservation = new HashMap<>();
                    reservation.put("nomeparking",nameprking);
                    reservation.put("nom", nom);
                    reservation.put("matricule", matricule);
                    reservation.put("date", date);
                    reservation.put("time",time);

                    // Ajouter les informations de réservation à Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DocumentReference userRef = db.collection("users").document(userId);


                    userRef.collection("reservations")
                            .add(reservation)
                            .addOnSuccessListener(documentReference -> {
                                Toast.makeText(Resarvatioon.this, "Réservation effectuée avec succès", Toast.LENGTH_SHORT).show();
                                afficherNotification(getApplicationContext(),nom);

                                // Réinitialiser les champs de texte après la réservation réussie

                                Intent b= getIntent();

                                long tarifii = b.getLongExtra("tarifh",0);
                                String nomedeparking = b.getStringExtra("Namepark");
                                 String wilaya = b.getStringExtra("wilaya");

                                nbrJours=findViewById(R.id.nbrJours);
                                nbrHeurs=findViewById(R.id.nbrHours);
                                Intent intent = new Intent(Resarvatioon.this, PaymentOnline.class);
                                long nbrJ = 0;
                                long nbrH = 0;
                                if (!nbrJours.getText().toString().isEmpty()) {
                                    nbrJ = Integer.parseInt(nbrJours.getText().toString());
                                }
                                if (!nbrHeurs.getText().toString().isEmpty()) {
                                    nbrH = Integer.parseInt(nbrHeurs.getText().toString());
                                }
                                long tarifT =(tarifii * nbrJ * 24) + ( tarifii * nbrH);
                                intent.putExtra("tarifh",tarifii);
                                intent.putExtra("TARIF_TOTAL", tarifT);
                                intent.putExtra("nomedeparking",nomedeparking);
                                intent.putExtra("wilaya",wilaya);
                                intent.putExtra("nom",nom);
                                intent.putExtra("matricule",matricule);
                                intent.putExtra("date",date);
                                intent.putExtra("time",time);
                                intent.putExtra("nmbrjour",nbrJ);
                                intent.putExtra("nombreheurs",nbrH);


                                startActivity(intent);









                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Resarvatioon.this, "Erreur lors de la réservation", Toast.LENGTH_SHORT).show();
                            });
                }



            }
        });




    }

    private void afficherNotification(Context context , String pakingname) {
        int notificationId = 1;
        String channelId = "channel_id";
        String channelName = "Channel Name";

        // Créez un texte pour la notification
        String notificationText = "L'opération s'est terminée avec succès "+pakingname;

        // Obtenez le gestionnaire de notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Vérifiez si le système d'exploitation est Android Oreo ou supérieur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Créez un canal de notification pour les versions d'Android Oreo et supérieures
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // Créez la notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentTitle("Notification")
                .setContentText(notificationText)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setAutoCancel(true);


        // Affichez la notification
        notificationManager.notify(notificationId, builder.build());
    }


}
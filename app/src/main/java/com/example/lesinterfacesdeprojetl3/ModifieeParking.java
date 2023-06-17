package com.example.lesinterfacesdeprojetl3;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class ModifieeParking extends AppCompatActivity {
    String[] item={ "Adrar", "Chlef", "Laghouat", "Oum El Bouaghi","Batna","Béjaïa","Biskra","Béchar","Blida", "Bouira", "Tamanrasset", "Tébessa", "Tlemcen",
            "Tiaret", "Tizi Ouzou", "Alger", "Djelfa", "Jijel", "Sétif", "Saïda", "Skikda", "Sidi Bel Abbès", "Annaba", "Guelma", "Constantine", "Médéa",
            "Mostaganem", "M'Sila", "Mascara", "Ouargla", "Oran", "El Bayadh", "Illizi", "Bordj Bou Arreridj", "Boumerdès", "El Tarf", "Tindouf","Tissemsilt",
            "El Oued", "Khenchela", "Souk Ahras", "Tipaza", "Mila", "Aïn Defla", "Naâma", "Aïn Témouchent", "Ghardaïa", "Relizane","Timimoun","Bordj Badji Mokhtar",
            "Ouled Djellal", "Béni Abbès", "In Salah", "Guezzam", "Touggourt","Djanet","El M'Ghair ","El Meniaa" };
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String>adapterItems;
    Button conf;
    EditText timeOpen;
    EditText timeClose;
    EditText namePark;
    EditText nbrPlace;

    EditText tarif;
    TextInputLayout ed;
    private int mHour,mMinute;
    private static final int REQUEST_PROFILE_PARKING = 1;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifiee_parking);
        timeOpen=findViewById(R.id.heurOvertureMod);
        timeClose=findViewById(R.id.heurFermitureMod);
        db = FirebaseFirestore.getInstance();
        conf=findViewById(R.id.btnConfirmationMod);
        namePark=findViewById(R.id.nomParkingMod);
        nbrPlace = findViewById(R.id.nbrPlaceMod);
        tarif=findViewById(R.id.tarifHeureMod);





        timeOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                mHour=calendar.get(Calendar.HOUR);
                mMinute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(ModifieeParking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        timeOpen.setText(h+":"+m);
                    }
                },mHour,mMinute,true);
                timePickerDialog.show();

            }
        });

        timeClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                mHour=calendar.get(Calendar.HOUR);
                mMinute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(ModifieeParking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        timeClose.setText(h+":"+m);
                    }
                },mHour,mMinute,true);
                timePickerDialog.show();

            }
        });


        Intent md = getIntent();
        String timeOpenText = md.getStringExtra("parkingTimeOpen");
        String timeCloseText = md.getStringExtra("parkingTimeClose");
        String nameParkText = md.getStringExtra("parkingName");
        int nbrPlaceText = md.getIntExtra("parkingNbrPlace",0);

        int tarifText = md.getIntExtra("parkingTarifHour",0);

        timeOpen=findViewById(R.id.heurOvertureMod);
        timeClose=findViewById(R.id.heurFermitureMod);

        conf=findViewById(R.id.btnConfirmationMod);
        namePark=findViewById(R.id.nomParkingMod);
        nbrPlace = findViewById(R.id.nbrPlaceMod);
        tarif=findViewById(R.id.tarifHeureMod);






        timeOpen.setText(timeOpenText);
        timeClose.setText(timeCloseText);
        namePark.setText(nameParkText);
        nbrPlace.setText(String.valueOf(nbrPlaceText));
        tarif.setText(String.valueOf(tarifText));


        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateParkingInformation();
            }
        });

    }




    private void updateParkingInformation() {
        String name = namePark.getText().toString();
        int places = Integer.parseInt(nbrPlace.getText().toString());
        int tarife = Integer.parseInt(tarif.getText().toString());
        String timeo =timeOpen.getText().toString();
        String timep =timeClose.getText().toString();

        // Update the parking information in Firestore
        db.collection("parkings")
                .document(name)
                .update("Nombre de places", places,"Hraire d'ouverture",timeo,"Heure de ferneture",timep,"Tarif",tarife)
                .addOnSuccessListener(aVoid -> {
                    // Update successful
                    Toast.makeText(ModifieeParking.this, "Parking information updated successfully", Toast.LENGTH_SHORT).show();
                    afficherNotification(getApplicationContext(),name);
                    setResult(RESULT_OK);
                    finish();
                })
                .addOnFailureListener(e -> {
                    // Update failed
                    Toast.makeText(ModifieeParking.this, "remplire tout les champs ou vous ne pouvesz pas changer le nom de parking ", Toast.LENGTH_SHORT).show();
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




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
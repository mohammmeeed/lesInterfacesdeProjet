package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Newparking extends AppCompatActivity {
    String[] item={ "Adrar", "Chlef", "Laghouat", "Oum El Bouaghi","Batna","Béjaïa","Biskra","Béchar","Blida", "Bouira", "Tamanrasset", "Tébessa", "Tlemcen",
            "Tiaret", "Tizi Ouzou", "Alger", "Djelfa", "Jijel", "Sétif", "Saïda", "Skikda", "Sidi Bel Abbès", "Annaba", "Guelma", "Constantine", "Médéa",
            "Mostaganem", "M'Sila", "Mascara", "Ouargla", "Oran", "El Bayadh", "Illizi", "Bordj Bou Arreridj", "Boumerdès", "El Tarf", "Tindouf","Tissemsilt",
            "El Oued", "Khenchela", "Souk Ahras", "Tipaza", "Mila", "Aïn Defla", "Naâma", "Aïn Témouchent", "Ghardaïa", "Relizane","Timimoun","Bordj Badji Mokhtar",
            "Ouled Djellal", "Béni Abbès", "In Salah", "Guezzam", "Touggourt","Djanet","El M'Ghair ","El Meniaa" };
    private EditText nameEditText, wilayaEditText, placesEditText, tarifEditText, latEditText, longEditText, ouvertureEditText, fermetureEditText;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String>adapterItems;
    TextInputLayout ed;
    private int mHour,mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newparking);
        Button saveButton = findViewById(R.id.btnConfirmation);


        autoCompleteTextView=findViewById(R.id.autoCText);

        nameEditText = findViewById(R.id.nomParking);  // nomParking

        placesEditText = findViewById(R.id.nbrPlace);//nbrPlace
        tarifEditText = findViewById(R.id.tarifHeure);//tarifHeure
        latEditText = findViewById(R.id.latitude);//latitude( glb binathome f xml)
        longEditText = findViewById(R.id.longitude);//longitude
        ouvertureEditText = findViewById(R.id.heurOverture);//heurOverture
        fermetureEditText = findViewById(R.id.heurFermiture);//heurFermiture

        ed=findViewById(R.id.autoCTextCase);

        adapterItems= new ArrayAdapter<String>(this, R.layout.list_wilaya, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sItem=adapterView.getItemAtPosition(i).toString();
                ed.setHint(sItem);
                Toast.makeText(Newparking.this, ""+sItem, Toast.LENGTH_SHORT).show();

            }
        });




        ouvertureEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                mHour=calendar.get(Calendar.HOUR);
                mMinute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(Newparking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        ouvertureEditText.setText(h+":"+m);
                        String ouverture = h+":"+m ;
                    }
                },mHour,mMinute,true);
                timePickerDialog.show();

            }
        });


        fermetureEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                mHour=calendar.get(Calendar.HOUR);
                mMinute=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(Newparking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        fermetureEditText.setText(h+":"+m);
                        String fermeture = h+":"+m;
                    }
                },mHour,mMinute,true);
                timePickerDialog.show();
            }
        });












































        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





















                String name = nameEditText.getText().toString();
                String wilaya = ed.getHint().toString();
                String placesStr = placesEditText.getText().toString();
                String tarifStr = tarifEditText.getText().toString();
                double latStr = Double.parseDouble(latEditText.getText().toString());
                double longStr = Double.parseDouble(longEditText.getText().toString());
                String ouverture = ouvertureEditText.getText().toString();
                String fermeture = fermetureEditText.getText().toString();




























                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(wilaya) || TextUtils.isEmpty(placesStr) ||
                        TextUtils.isEmpty(tarifStr) ||
                        TextUtils.isEmpty(ouverture) || TextUtils.isEmpty(fermeture)) {
                    Toast.makeText(Newparking.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    int places = Integer.parseInt(placesStr);
                    int tarif = Integer.parseInt(tarifStr);
                    double lat = Double.parseDouble(String.valueOf(latStr));
                    double lng = Double.parseDouble(String.valueOf(longStr));








                    // Create a new HashMap or model class object to store the parking information
                    Map<String, Object> parking = new HashMap<>();
                    parking.put("Name", name);
                    parking.put("Wilaya", wilaya);
                    parking.put("Nombre de places", places);
                    parking.put("Tarif", tarif);
                    parking.put("Localisation", new GeoPoint(lat, lng));
                    parking.put("Hraire d'ouverture", ouverture);
                    parking.put("Heure de ferneture", fermeture);
                    parking.put("Director", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    // Add the parking information to Firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference parkings = db.collection("parkings");
                    parkings.document(name)
                            .set(parking)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(Newparking.this, "Parking ajouté avec succès", Toast.LENGTH_SHORT).show();
                                afficherNotification(getApplicationContext(),name);
                                // Reset the input fields after successful addition
                                nameEditText.setText("");
                                ed.setHint("");
                                placesEditText.setText("");
                                tarifEditText.setText("");
                                latEditText.setText("");
                                longEditText.setText("");
                                ouvertureEditText.setText("");
                                fermetureEditText.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Newparking.this, "Erreur lors de l'ajout du parking", Toast.LENGTH_SHORT).show();
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
        String notificationText = "L'opération s'est terminée avec succès"+pakingname;

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
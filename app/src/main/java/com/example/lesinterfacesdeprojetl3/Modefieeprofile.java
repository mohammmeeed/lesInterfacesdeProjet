package com.example.lesinterfacesdeprojetl3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Modefieeprofile extends AppCompatActivity {
    EditText newnametxt , newnembertxt ;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modefieeprofile);
        newnametxt = findViewById(R.id.newname);
        newnembertxt = findViewById(R.id.newnmbr);
      Button modifier=findViewById(R.id.modifierrr);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();


        DocumentReference userRef = db.collection("Users").document(userId);
        userRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String number = documentSnapshot.getString("phone");

                String name = documentSnapshot.getString("name");

                // Populate the EditText fields with the existing values

                newnametxt.setText(name);
                newnembertxt.setText(number);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newnametxt.getText().toString();
                String number = newnembertxt.getText().toString();
                Map<String, Object> updatedData = new HashMap<>();

                updatedData.put("name", name);
                updatedData.put("phone",number);
                userRef.update(updatedData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(Modefieeprofile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                            afficherNotification(getApplicationContext(),name);

                            // Handle the success scenario, such as navigating to another activity
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(Modefieeprofile.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                            // Handle the failure scenario, such as displaying an error message
                        });

            }
        });







        // ...

        Button delete;

// ...

        delete = findViewById(R.id.delete);

// ...

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a confirmation dialog to the user
                AlertDialog.Builder builder = new AlertDialog.Builder(Modefieeprofile.this);
                builder.setTitle("Delete Profile");
                builder.setMessage("Are you sure you want to delete your profile?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the user profile from Firestore
                        userRef.delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Delete the user's authentication record
                                        auth.getCurrentUser().delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(Modefieeprofile.this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Modefieeprofile.this,Login.class));
                                                        // Handle the success scenario, such as navigating to another activity
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(Modefieeprofile.this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
                                                        // Handle the failure scenario, such as displaying an error message
                                                    }
                                                });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Modefieeprofile.this, "Failed to delete profile", Toast.LENGTH_SHORT).show();
                                        // Handle the failure scenario, such as displaying an error message
                                    }
                                });
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

// ...






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
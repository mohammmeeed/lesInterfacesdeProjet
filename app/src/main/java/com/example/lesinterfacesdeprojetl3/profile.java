package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    private TextView textViewName, usertyptxt;
    private TextView textViewEmail;
    private TextView textViewPhone;
    private TextView  dcnct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usertyptxt = findViewById(R.id.userType);
        dcnct = findViewById(R.id.deconnecte);
        textViewName = findViewById(R.id.userName);
        textViewEmail = findViewById(R.id.userEmail);
        textViewPhone = findViewById(R.id.userPhone);
        CircleImageView imgViewProfile = findViewById(R.id.imgview_profile);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Remplacez par l'ID réel de l'utilisateur
        db.collection("Users").document(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Récupérer les valeurs des champs nom, email et téléphone

                            String userType = document.getString("usertype");
                            String nom = document.getString("name");
                            String email = document.getString("email");
                            String phone = document.getString("phone");

                            // Afficher les informations de l'utilisateur dans les TextView
                            textViewName.setText(nom);
                            textViewEmail.setText(email);
                            textViewPhone.setText(phone);
                            usertyptxt.setText(userType);


                            // Update the profile image based on user type
                            if (userType != null && userType.equals("utilisateur")) {
                                imgViewProfile.setImageResource(R.drawable.utilisateur);
                            } else if (userType != null && userType.equals("Proprietaire")) {
                                imgViewProfile.setImageResource(R.drawable.propreitaire);
                            }


                        }
                    }
                });





dcnct.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(profile.this,Login.class));
    }
});


        final ImageView back1 = findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this,MainActivity.class));
            }
        });

        Button mdf = findViewById(R.id.btnModif);
        mdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this,Modefieeprofile.class);
                startActivity(intent);

            }
        });

    }
}
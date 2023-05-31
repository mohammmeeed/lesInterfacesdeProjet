package com.example.lesinterfacesdeprojetl3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SinginGoogleFacebook extends AppCompatActivity {
 Button sing;
    TextView btnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin_google_facebook);
        sing=findViewById(R.id.singbtn);
        btnt=findViewById(R.id.alreadyHaveAcc);
        sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SinginGoogleFacebook.this,Signin.class));
            }
        });

    btnt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(SinginGoogleFacebook.this,Login.class));
        }
    });
}}



package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView btn;
    EditText inputEmail,inputPassword;
    Button btnLogin;
    ProgressDialog mLoadingBar;


    private EditText email , password ;
    private Button connextion ;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        btn=findViewById(R.id.textViewSignup);
        inputEmail=findViewById(R.id.inputLmail);
        inputPassword=findViewById(R.id.inputLPassword);
        btnLogin=findViewById(R.id.singbtn);
        mLoadingBar=new ProgressDialog(Login.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
String email =inputEmail.getText().toString();
String password = inputPassword.getText().toString();
                checkCrededentials();
                Utilisateur(email,password);


            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SinginGoogleFacebook.class));
            }
        });
    }

    private void checkCrededentials() {

        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();


        if(email.isEmpty() || !email.contains("@")){
            showError(inputEmail,"votre Email adress n'est pas valide!");
        }
        else if (password.isEmpty() || password.length()<8 ) {
            showError(inputPassword,"votre mot de passe n'est pas valide!(doit contenir au moins 8 caractères)");
        }
        else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("veuillez patienter pendant que vous vérifiez vos informations d'identification.");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();


        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
    private void Utilisateur(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this, "connextion", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,Welcom.class));
                finish();
            }
        });
    }

}


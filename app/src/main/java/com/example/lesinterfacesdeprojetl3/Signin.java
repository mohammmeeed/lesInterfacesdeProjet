package com.example.lesinterfacesdeprojetl3;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signin extends AppCompatActivity {
    TextView btn;
    RadioGroup rG;

    private EditText inputUser;
    private EditText inputEmail;
    private EditText inputPhone, inputLPassword, inputConfPass;
    private Button btnSingin;
    RadioGroup userTypeRadioGroup;

    private FirebaseFirestore db;

    private ProgressDialog mLoadingBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btn = findViewById(R.id.alreadyHaveAcc);
        mLoadingBar = new ProgressDialog(this);

        userTypeRadioGroup = findViewById(R.id.radioGroup);

        inputConfPass = findViewById(R.id.inputCPassword);
        mLoadingBar = new ProgressDialog(Signin.this);
        db = FirebaseFirestore.getInstance();

        inputUser = findViewById(R.id.inputUser);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        btnSingin = findViewById(R.id.btnSingin);
        inputLPassword = findViewById(R.id.inputLPassword);

        auth = FirebaseAuth.getInstance();

        btnSingin = findViewById(R.id.btnSingin);
        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = inputUser.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();
                String password = inputLPassword.getText().toString().trim();
                String confirmPassword = inputConfPass.getText().toString().trim();

                int selectedRadioButtonId = userTypeRadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.rb1) {

                    createNewUtilisateur( email, password,username, phone);
                } else if (selectedRadioButtonId == R.id.rb2) {
                    startActivity(new Intent(Signin.this, Login2.class));
                    createNewUtilisateuree(email,password,username,phone);
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signin.this, Login.class));
            }
        });
    }

    private void checkCredentials() {
        String username = inputUser.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputLPassword.getText().toString();
        String phone = inputPhone.getText().toString();
        String condPhoneNbr = "^[+]\\d{10,13}$";
        String confirmPassword = inputConfPass.getText().toString();

        if (username.isEmpty() || username.length() < 7) {
            showError(inputUser, "Votre nom d'utilisateur n'est pas valide! (doit contenir au moins 7 caractères)");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Votre adresse e-mail n'est pas valide!");
        } else if (phone.isEmpty() || !phone.matches(condPhoneNbr)) {
            showError(inputPhone, "Votre numéro de téléphone n'est pas valide");
        } else if (password.isEmpty() || password.length() < 8) {
            showError(inputLPassword, "Votre mot de passe n'est pas valide! (doit contenir au moins 8 caractères)");
        } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            showError(inputConfPass, "Le mot de passe ne correspond pas");
        } else {
            mLoadingBar.setTitle("Inscription");
            mLoadingBar.setMessage("Veuillez patienter pendant que nous vérifions vos informations d'identification.");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

        }
    }

    private void showError(EditText input, String errorMessage) {
        input.setError(errorMessage);
        input.requestFocus();
    }

    private void createNewUtilisateur(String email, String password, String name, String phone) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String userId = auth.getCurrentUser().getUid();

                    // Create a new user with information
                    Map<String, Object> user = new HashMap<>();
                    user.put("email", email);
                    user.put("name", name);

                    user.put("password", password);
                    user.put("usertype", "utilisateur");
                    user.put("phone", phone);

                    // Save the user information to Firestore
                    db.collection("Users")
                            .document(userId)
                            .set(user)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(Signin.this, "Nouveau compte créé", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Signin.this, Welcom.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Signin.this, "Erreur lors de l'enregistrement des informations de l'utilisateur", Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Signin.this, "Erreur lors de la création du compte utilisateur", Toast.LENGTH_SHORT).show();
                });
    }

    private void createNewUtilisateuree(String email, String password, String name, String phone) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String userId = auth.getCurrentUser().getUid();

                    // Create a new user with information
                    Map<String, Object> user = new HashMap<>();
                    user.put("email", email);
                    user.put("name", name);

                    user.put("password", password);
                    user.put("usertype", "Proprietaire");
                    user.put("phone", phone);

                    // Save the user information to Firestore
                    db.collection("Users")
                            .document(userId)
                            .set(user)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(Signin.this, "Nouveau compte créé", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Signin.this, Welcom.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Signin.this, "Erreur lors de l'enregistrement des informations de l'utilisateur", Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Signin.this, "Erreur lors de la création du compte utilisateur", Toast.LENGTH_SHORT).show();
                });
    }
}

package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class CardPaiment extends AppCompatActivity {

    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_paiment);

        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btnBuy);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS est requis sur ce numéro")
                .setup(CardPaiment.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CardPaiment.this);
                    alertBuilder.setTitle("Confirmer avant l'achat");
                    alertBuilder.setMessage("numéro de carte: " + cardForm.getCardNumber() + "\n" +
                            "Date d'expiration de la carte: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Carte CVV: " + cardForm.getCvv() + "\n" +
                            "Code Postal: " + cardForm.getPostalCode() + "\n" +
                            "Numéro de téléphone: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(CardPaiment.this, "Merci pour l'achat", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }else {
                    Toast.makeText(CardPaiment.this, "Veuillez remplir le formulaire", Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}
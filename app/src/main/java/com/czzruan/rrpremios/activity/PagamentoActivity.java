package com.czzruan.rrpremios.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.czzruan.rrpremios.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class PagamentoActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference nomesRef = db.collection("MP");

    private Button buttonEnviarComprovante;
    private CardView cardMercadoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        getSupportActionBar().hide();

        cardMercadoPago = findViewById(R.id.cardMercadoPago);

        cardMercadoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




               Uri uri = Uri.parse("api.mercadopago");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.mercadopago.android");
                try { startActivity(likeIng);
                } catch (ActivityNotFoundException e)
                { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mercadopago.com.br/checkout/v1/redirect?pref_id=65834224-97829759-7ab1-4360-918d-5461f27f1173"))); }

            }
        });

        buttonEnviarComprovante = findViewById(R.id.buttonEnviarComprovante);
        buttonEnviarComprovante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("api.whatsapp");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.whatsapp.android");
                try { startActivity(likeIng);
                } catch (ActivityNotFoundException e)
                { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://api.whatsapp.com/send?1=pt_BR&phone=5595991412223"))); }

            }
        });

    }

}

package com.czzruan.rrpremios.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.czzruan.rrpremios.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InicioActivity extends AppCompatActivity {

    private Button buttonParticipar, buttonComoFunciona, buttonResultados, buttonSobre, buttonTrocarConta, buttonSair;
    private ImageView imagemPremio;

    private FirebaseAuth usuario = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getSupportActionBar().hide();
        inicializarComponetes();


        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = storageReference.child("imagempremio");
        StorageReference imaRef = imagens.child("premio.png");

        imaRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Glide.with(InicioActivity.this).load(uri).into(imagemPremio);
            }
        });
        buttonParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EscolherNumeroActivity.class);
                startActivity(i);
            }
        });
        buttonComoFunciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ComoFuncionaActivity.class);
                startActivity(i);
            }
        });
        buttonResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ResultadosActivity.class);
                startActivity(i);
            }
        });
        buttonSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SobreActivity.class);
                startActivity(i);
            }
        });
        buttonTrocarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.signOut();
                finish();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void inicializarComponetes() {
        buttonParticipar = findViewById(R.id.buttonParticipar);
        buttonComoFunciona = findViewById(R.id.buttonComoFunciona);
        buttonResultados = findViewById(R.id.buttonResultados);
        buttonSobre = findViewById(R.id.buttonSobre);
        buttonTrocarConta = findViewById(R.id.buttonTrocarConta);
        buttonSair = findViewById(R.id.buttonSair);
        imagemPremio = findViewById(R.id.imagePremio);
    }
}




package com.czzruan.rrpremios.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.czzruan.rrpremios.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class EnviarEmailActivity extends AppCompatActivity {

    private EditText editRecuperarEmail;
    private Button buttonRecuperarEviar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_email);
        getSupportActionBar().hide();

        editRecuperarEmail = findViewById(R.id.editRecuperarEmail);
        buttonRecuperarEviar = findViewById(R.id.buttonRecuperarEnviar);

        firebaseAuth = firebaseAuth.getInstance();

        buttonRecuperarEviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void reset() {

        firebaseAuth
                .sendPasswordResetEmail(editRecuperarEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(EnviarEmailActivity.this, "Email Enviado!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(EnviarEmailActivity.this, "Email Não Enviado!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}

package com.czzruan.rrpremios.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.czzruan.rrpremios.R;
import com.czzruan.rrpremios.helper.ConfiguracaoFirebase;
import com.czzruan.rrpremios.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button buttonEntrar;
    private EditText editEmail;
    private EditText editSenha;
    private TextView textEsqueceu;
    private TextView textCadastrar;
    private ProgressBar progressLogin;

    private Usuario usuario;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        inicializarComponentes();
        verificarUsuarioLogado();


        progressLogin.setVisibility(View.INVISIBLE);
        //Fazer login do usuario
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = editEmail.getText().toString();
                String textoSenha = editSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin(usuario);


                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a Senha!",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha o Email!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        textEsqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EnviarEmailActivity.class);
                startActivity(i);
            }
        });
    }



    private void inicializarComponentes() {
        buttonEntrar = findViewById(R.id.buttonEntrar);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        textEsqueceu = findViewById(R.id.textEsqueceu);
        textCadastrar = findViewById(R.id.textCadastrar);
        progressLogin = findViewById(R.id.progressLogin);


    }

    public void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), InicioActivity.class));
            finish();
        }
    }

    public void validarLogin(Usuario usuario) {

        progressLogin.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressLogin.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this,
                            "Erro ao fazer login!",
                            Toast.LENGTH_SHORT).show();
                    progressLogin.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    public void abrirCadastro(View view) {

        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
        finish();


    }

}

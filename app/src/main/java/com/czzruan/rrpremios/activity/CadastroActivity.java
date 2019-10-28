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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoCelular, campoSenha, campoConfirmarSenha;
    private Button buttonCadastrar;
    private TextView textTenhoCadastro;
    private ProgressBar progressCadastro;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        inicializarComponentes();
        progressCadastro.setVisibility(View.INVISIBLE);

        textTenhoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        //cadastro usuario
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                String textoCelular = campoCelular.getText().toString();
                String textoConfimarSenha = campoConfirmarSenha.getText().toString();

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoCelular.isEmpty()) {
                            if (!textoSenha.isEmpty()) {
                                if (!textoConfimarSenha.isEmpty()) {

                                    if (textoSenha.equals(textoConfimarSenha)) {
                                        if (textoConfimarSenha.equals(textoSenha)) {

                                            usuario = new Usuario();
                                            usuario.setNome(textoNome);
                                            usuario.setEmail(textoEmail);
                                            usuario.setCelular(textoCelular);
                                            usuario.setSenha(textoSenha);
                                            usuario.setConfirmarSenha(textoConfimarSenha);
                                            cadastrar(usuario);

                                        } else {
                                            Toast.makeText(CadastroActivity.this, "Senhas não conferem!!!", Toast.LENGTH_SHORT).show();
                                        }


                                    } else {
                                        Toast.makeText(CadastroActivity.this, "Senhas não conferem!", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(CadastroActivity.this, "Confirme a Senha!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CadastroActivity.this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(CadastroActivity.this, "Preencha o Celular!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha o Email!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*Método responsabel por cadastrar usuarios com email e senha e fazer validações ao fazer cadastro*/

    public void cadastrar(final Usuario usuario) {
        progressCadastro.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()

        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressCadastro.setVisibility(View.INVISIBLE);

                            try {

                                //Salvar dados no firebase
                                String idUsuario = task.getResult().getUser().getUid();
                                usuario.setId(idUsuario);
                                usuario.salvar();

                                Toast.makeText(CadastroActivity.this, "Cadastro com sucesso!",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                                finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            progressCadastro.setVisibility(View.INVISIBLE);
                            String erroExcecao = "";
                            try {
                                throw task.getException();

                            } catch (FirebaseAuthWeakPasswordException e) {
                                erroExcecao = "Digite uma senha mais forte!";

                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcecao = "Por favor, digite um e-mail válido!";

                            } catch (FirebaseAuthUserCollisionException e) {
                                erroExcecao = "Esta conta já foi cadastrada!";

                            } catch (Exception e) {
                                erroExcecao = "Não foi possível o Cadastro! " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(CadastroActivity.this, "Erro! " + erroExcecao, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void inicializarComponentes() {
        campoNome = findViewById(R.id.editCadastroNome);
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoCelular = findViewById(R.id.editCadastroCelular);
        campoSenha = findViewById(R.id.editCadastroSenha);
        campoConfirmarSenha = findViewById(R.id.editCadastroConfirmarSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastroCadastrar);
        textTenhoCadastro = findViewById(R.id.editCadastroTenhoCadastro);
        progressCadastro = findViewById(R.id.progressCadastro);
    }


}

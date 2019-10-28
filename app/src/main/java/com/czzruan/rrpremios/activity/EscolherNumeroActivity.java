package com.czzruan.rrpremios.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czzruan.rrpremios.R;
import com.czzruan.rrpremios.adapter.AdapterAtualizacaoRestante;
import com.czzruan.rrpremios.adapter.AdapterNomes;
import com.czzruan.rrpremios.model.AtualizacaoRestante;
import com.czzruan.rrpremios.model.Nomes;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class EscolherNumeroActivity extends AppCompatActivity {

    private FirebaseFirestore dbAtualizacaoRestante = FirebaseFirestore.getInstance();
    private CollectionReference atualizacaoRestanteRef = dbAtualizacaoRestante.collection("AtualizacaoRestante");
    private AdapterAtualizacaoRestante adapterAtualizacaoRestante;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference nomesRef = db.collection("NomesParticipantes");
    private AdapterNomes adapterNomes;
    private Button buttonParticiparRifa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);
        getSupportActionBar().hide();



        buttonParticiparRifa = findViewById(R.id.buttorEscolharParticipar);
        buttonParticiparRifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PagamentoActivity.class);
                startActivity(i);
            }
        });

        setUpRecyclerView();
        setUpRecyclerViewResultadoRestante();
    }


    private void setUpRecyclerViewResultadoRestante() {
        Query query = atualizacaoRestanteRef.orderBy("atualizacao", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<AtualizacaoRestante> optionsAtualizacao = new FirestoreRecyclerOptions.Builder<AtualizacaoRestante>()
                .setQuery(query, AtualizacaoRestante.class)
                .build();

        adapterAtualizacaoRestante = new AdapterAtualizacaoRestante(optionsAtualizacao);
        RecyclerView recyclerViewAtualizacaoRestante = findViewById(R.id.recyclerViewResultado);
        recyclerViewAtualizacaoRestante.setHasFixedSize(true);
        recyclerViewAtualizacaoRestante.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAtualizacaoRestante.setAdapter(adapterAtualizacaoRestante);

    }

    private void setUpRecyclerView() {
        Query query = nomesRef.orderBy("numero", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Nomes> options = new FirestoreRecyclerOptions.Builder<Nomes>()
                .setQuery(query, Nomes.class)
                .build();

        adapterNomes = new AdapterNomes(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewNomes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterNomes);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterNomes.startListening();
        adapterAtualizacaoRestante.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapterNomes.stopListening();
        adapterAtualizacaoRestante.stopListening();
    }


}


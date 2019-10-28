package com.czzruan.rrpremios.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.czzruan.rrpremios.R;
import com.czzruan.rrpremios.adapter.AdapterResultados;
import com.czzruan.rrpremios.model.Resultado;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

public class ResultadosActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference resultadoRef = db.collection("Resultados");
    private AdapterResultados adapterResultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        getSupportActionBar().hide();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        Query query = resultadoRef.orderBy("extracao", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Resultado> options = new FirestoreRecyclerOptions.Builder<Resultado>()
                .setQuery(query, Resultado.class)
                .build();

        adapterResultados = new AdapterResultados(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerResultados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterResultados);

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterResultados.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapterResultados.stopListening();
    }
}
package com.czzruan.rrpremios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.czzruan.rrpremios.R;
import com.czzruan.rrpremios.activity.EscolherNumeroActivity;
import com.czzruan.rrpremios.model.Nomes;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterNomes extends FirestoreRecyclerAdapter<Nomes, AdapterNomes.NomesHolder> {

    public AdapterNomes(@NonNull FirestoreRecyclerOptions<Nomes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NomesHolder nomeHolder, int i, @NonNull Nomes nomes) {
        nomeHolder.textViewNome.setText(String.valueOf(nomes.getNome()));
        nomeHolder.textViewStatus.setText(nomes.getStatus());
        nomeHolder.textViewNumero.setText(nomes.getNumero());
    }

    @NonNull
    @Override
    public NomesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista,
                parent, false);
        return new NomesHolder(view);
    }

    class NomesHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewStatus;
        TextView textViewNumero;



        public NomesHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textNome);
            textViewStatus = itemView.findViewById(R.id.textStatus);
            textViewNumero = itemView.findViewById(R.id.textNumero);



        }
    }
}
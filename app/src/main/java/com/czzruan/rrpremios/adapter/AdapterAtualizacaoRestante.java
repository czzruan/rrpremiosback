package com.czzruan.rrpremios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.czzruan.rrpremios.R;
import com.czzruan.rrpremios.model.AtualizacaoRestante;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterAtualizacaoRestante extends FirestoreRecyclerAdapter<AtualizacaoRestante, AdapterAtualizacaoRestante.AdapterAtualizacaoRestanteHolder>  {



    public AdapterAtualizacaoRestante(@NonNull FirestoreRecyclerOptions<AtualizacaoRestante> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterAtualizacaoRestanteHolder adapterAtualizacaoRestanteHolder, int i, @NonNull AtualizacaoRestante atualizacaoRestante) {

        adapterAtualizacaoRestanteHolder.textAtualizacao.setText(atualizacaoRestante.getAtualizacao());
        adapterAtualizacaoRestanteHolder.textRestante.setText(atualizacaoRestante.getRestante());

    }

    @NonNull
    @Override
    public AdapterAtualizacaoRestanteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.atualizacaorestante_adapter,
                parent, false);

        return new AdapterAtualizacaoRestanteHolder(v);
    }

    class AdapterAtualizacaoRestanteHolder extends RecyclerView.ViewHolder{

        TextView textAtualizacao;
        TextView textRestante;


        public AdapterAtualizacaoRestanteHolder(@NonNull View itemView) {
            super(itemView);

            textAtualizacao = itemView.findViewById(R.id.atualizacao);
            textRestante = itemView.findViewById(R.id.restante);

        }
    }

}

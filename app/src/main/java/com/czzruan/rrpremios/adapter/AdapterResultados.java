package com.czzruan.rrpremios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.czzruan.rrpremios.R;
import com.czzruan.rrpremios.model.Resultado;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterResultados extends FirestoreRecyclerAdapter<Resultado, AdapterResultados.ResultadoHolder> {

    public AdapterResultados(@NonNull FirestoreRecyclerOptions<Resultado> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ResultadoHolder resultadoHolder, int i, @NonNull Resultado resultado) {
        resultadoHolder.textViewExtracao.setText(String.valueOf(resultado.getExtracao()));
        resultadoHolder.textViewData.setText(resultado.getData());
        resultadoHolder.textViewPremio.setText(resultado.getPremio());
        resultadoHolder.textViewGanhador.setText(resultado.getGanhador());
        resultadoHolder.textViewNumero.setText(resultado.getNumero());
    }

    @NonNull
    @Override
    public ResultadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultado_adapter,
                parent, false);
        return new ResultadoHolder(view);
    }

    class ResultadoHolder extends RecyclerView.ViewHolder {
        TextView textViewExtracao;
        TextView textViewData;
        TextView textViewPremio;
        TextView textViewGanhador;
        TextView textViewNumero;

        public ResultadoHolder(@NonNull View itemView) {
            super(itemView);
            textViewExtracao = itemView.findViewById(R.id.textExtracao);
            textViewData = itemView.findViewById(R.id.textData);
            textViewPremio = itemView.findViewById(R.id.textPremio);
            textViewGanhador = itemView.findViewById(R.id.textGanhador);
            textViewNumero = itemView.findViewById(R.id.textNumero);


        }
    }
}

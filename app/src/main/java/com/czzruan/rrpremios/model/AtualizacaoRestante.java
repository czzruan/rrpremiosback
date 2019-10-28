package com.czzruan.rrpremios.model;

public class AtualizacaoRestante {
    private String atualizacao;
    private String restante;

    public AtualizacaoRestante() {
    }


    public AtualizacaoRestante(String atualizacao, String restante){
        this.atualizacao = atualizacao;
        this.restante = restante;

    }

    public String getAtualizacao() {
        return atualizacao;
    }

    public String getRestante() {
        return restante;
    }
}
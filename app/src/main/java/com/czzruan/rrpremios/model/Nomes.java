package com.czzruan.rrpremios.model;

public class Nomes {

    private String nome;
    private String status;
    private String numero;

    public Nomes() {
        //empty constructor needed
    }

    public Nomes(String nome, String status, String numero) {
        this.nome = nome;
        this.status = status;
        this.numero = numero;

    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public String getNumero() {
        return numero;
    }
}

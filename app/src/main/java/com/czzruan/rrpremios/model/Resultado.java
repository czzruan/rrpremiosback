package com.czzruan.rrpremios.model;

public class Resultado {

    private String extracao;
    private String data;
    private String premio;
    private String ganhador;
    private String numero;

    public Resultado() {
        //empty constructor needed
    }

    public Resultado(String extracao, String data, String premio, String ganhador, String numero){
        this.extracao = extracao;
        this.data = data;
        this.premio = premio;
        this.ganhador = ganhador;
        this.numero = numero;
    }

    public String getExtracao() {
        return extracao;
    }

    public String getData() {
        return data;
    }

    public String getPremio() {
        return premio;
    }

    public String getGanhador() {
        return ganhador;
    }

    public String getNumero() {
        return numero;
    }
}

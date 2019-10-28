package com.czzruan.rrpremios.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaDatabase;
    private static FirebaseAuth referenciaAutenticacao;
    private static StorageReference referenciaStorage;

    //retornar a referencia do database

    public static DatabaseReference getReferenciaFirebase(){
        if (referenciaDatabase == null){
            referenciaDatabase = FirebaseDatabase.getInstance().getReference();
        }
        return  referenciaDatabase;
    }
    //retorna a instacia do FirebaseAuth
    public static  FirebaseAuth getReferenciaAutenticacao(){
        if(referenciaAutenticacao == null){
            referenciaAutenticacao = FirebaseAuth.getInstance();

        }
        return referenciaAutenticacao;
    }
    public static StorageReference getReferenciaStorage(){

        if (referenciaStorage == null){
            referenciaStorage = FirebaseStorage.getInstance().getReference();

        }
        return referenciaStorage;
    }

}

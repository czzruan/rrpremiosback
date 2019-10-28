package com.czzruan.rrpremios.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.czzruan.rrpremios.R;

public class ComoFuncionaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_funciona);
        getSupportActionBar().hide();
        TextView text = findViewById(R.id.linkFederal);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

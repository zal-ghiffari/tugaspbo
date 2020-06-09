package com.mngsoft.viginerezal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tentangsaya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentangsaya);
    }

    public void halamanmenu(View v){
        Intent i = new Intent(Tentangsaya.this, MainActivity.class);
        startActivity(i);
    }
}

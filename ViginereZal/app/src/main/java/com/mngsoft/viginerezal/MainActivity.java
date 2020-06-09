package com.mngsoft.viginerezal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void halamanenkripsi(View v){
        Intent i = new Intent(MainActivity.this, Enkripsi.class);
        startActivity(i);
    }

    public void halamandekripsi(View v){
        Intent i = new Intent(MainActivity.this, Dekripsi.class);
        startActivity(i);
    }

    public void tentangsaya(View v){
        Intent i = new Intent(MainActivity.this, Tentangsaya.class);
        startActivity(i);
    }

    public void hubungisaya (View v) {
        Uri uri = Uri.parse("http://t.me/ktrnxx");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

}

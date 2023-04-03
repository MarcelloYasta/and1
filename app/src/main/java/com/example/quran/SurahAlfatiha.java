package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SurahAlfatiha extends AppCompatActivity {

    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.laman_surah);

        findViewById(R.id.hm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SurahAlfatiha.this, ActivityMain.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.hs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SurahAlfatiha.this, History.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SurahAlfatiha.this, Acc.class);
                startActivity(intent);
            }
        });

    }

}

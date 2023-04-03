package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Acc extends AppCompatActivity {

    protected void onCreate(Bundle SavedInstaceState) {
        super.onCreate(SavedInstaceState);
        setContentView(R.layout.acc);

        findViewById(R.id.hm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Acc.this, ActivityMain.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.hs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Acc.this, History.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Acc.this, Acc.class);
                startActivity(intent);
            }
        });

    }

}

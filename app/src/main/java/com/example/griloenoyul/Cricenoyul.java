package com.example.griloenoyul;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cricenoyul extends AppCompatActivity {

    Button navcric,navgames;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricenoyul);

        navcric = findViewById(R.id.navtocric);
        navgames = findViewById(R.id.navtogames);

        myDialog = new Dialog(this);

    }

    public void navCric(View view) {

        Intent ic = new Intent(Cricenoyul.this,MainActivity.class);
        startActivity(ic);

    }

    public void navGames(View view) {

        TextView close;
        Button koentouro , flygame;
        myDialog.setContentView(R.layout.selgame);
        koentouro = (Button) myDialog.findViewById(R.id.buttbullcow);
        flygame = (Button) myDialog.findViewById(R.id.butthelecop);
        close =(TextView) myDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        koentouro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent koin = new Intent(Cricenoyul.this,KoEnTouro.class);
                startActivity(koin);

            }
        });

        flygame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gh = new Intent(Cricenoyul.this,GlideHigh.class);
                startActivity(gh);

            }
        });

        myDialog.setCancelable(false);
        myDialog.show();

    }
}
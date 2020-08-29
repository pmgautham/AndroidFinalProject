package com.example.griloenoyul;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class KoEnTouro extends AppCompatActivity {
    Button b_minus_a,b_minus_b,b_minus_c,b_plus_a,b_plus_b,b_plus_c;
    Button b_check,b_resign;
    TextView tv_number_a,tv_number_b,tv_number_c;
    TextView tv_info,tv_output;
    Random r;
    String txt = "";
    int guessA=1,guessB=2,guessC=3;
    int generatedA,generatedB,generatedC;
    int bulls=0;
    int cows=0;
    int tries=0;
    String output="";
    Dialog myDialog,winDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ko_en_touro);
        b_minus_a=findViewById(R.id.b_minus_a);
        b_minus_b=findViewById(R.id.b_minus_b);
        b_minus_c=findViewById(R.id.b_minus_c);
        b_plus_a=findViewById(R.id.b_plus_a);
        b_plus_b=findViewById(R.id.b_plus_b);
        b_plus_c=findViewById(R.id.b_plus_c);
        b_check=findViewById(R.id.b_check);
        b_resign=findViewById(R.id.b_resign);
        tv_number_a=findViewById(R.id.tv_number_a);
        tv_number_b=findViewById(R.id.tv_number_b);
        tv_number_c=findViewById(R.id.tv_number_c);
        tv_info=findViewById(R.id.tv_info);
        tv_output=findViewById(R.id.tv_output);

        r=new Random();
        myDialog = new Dialog(this);
        winDialog = new Dialog(this);
        generateNumber();
        b_minus_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(guessA>1)
                {
                    guessA--;
                }
                tv_number_a.setText(""+guessA);

            }
        });


        b_minus_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(guessB>1)
                {
                    guessB--;
                }
                tv_number_b.setText(""+guessB);


            }
        });


        b_minus_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(guessC>1)
                {
                    guessC--;
                }
                tv_number_c.setText(""+guessC);


            }
        });






        b_plus_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(guessA<9)
                {
                    guessA++;
                }
                tv_number_a.setText(""+guessA);

            }
        });


        b_plus_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(guessB<9)
                {
                    guessB++;
                }
                tv_number_b.setText(""+guessB);

            }
        });


        b_plus_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(guessC<9)
                {
                    guessC++;
                }
                tv_number_c.setText(""+guessC);

            }
        });
        b_resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_resign.setEnabled(false);
                b_check.setEnabled(false);
                txt = "YOU LOST! THE NUMBER IS: "+generatedA +""+generatedB +""+generatedC;
                winorlose(txt);

            }
        });
        b_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(guessA==guessB || guessA==guessC || guessB==guessC)
                {
                    tv_info.setText("Invalid Input");

                }
                else
                {
                    tries++;
                    checkNumber();
                    checkwin();
                    bulls=0;
                    cows=0;

                }

            }
        });

    }

    private void checkwin() {

        if(bulls==3){
            b_resign.setEnabled(false);
            b_check.setEnabled(false);

            txt  = "YOU WON THE GAME IN "+tries+" TRIES!";
            winorlose(txt);
        }
    }


    private void generateNumber()
    {
        //Fisrt number generate
        generatedA=r.nextInt(9)+1;
        do {
            generatedB=r.nextInt(9)+1;
        }while (generatedA == generatedB);
        do {
            generatedC=r.nextInt(9)+1;
        }while (generatedC == generatedA || generatedC == generatedB);


    }

    private void checkNumber() {
        //bulls
        if(guessA == generatedA)
        {
            bulls++;
        }

        if(guessB == generatedB)
        {
            bulls++;
        }
        if(guessC==generatedC)
        {
            bulls++;
        }
        //COWS
        if(guessA==generatedB || guessA==generatedC)
        {
            cows++;
        }

        if(guessB==generatedA || guessB==generatedC)
        {
            cows++;
        }

        if(guessC==generatedA || guessC==generatedB)
        {
            cows++;
        }
        output=output+""+tries+". "+guessA+""+guessB+""+guessC+" -BULLS: "+bulls+",COW: "+cows +"\n";
        tv_output.setText(output);

        if (tries == 5)
        {
            b_resign.setEnabled(false);
            b_check.setEnabled(false);
            txt = "YOU LOST! THE NUMBER IS: "+generatedA +""+generatedB +""+generatedC;
            winorlose(txt);
        }

    }





    public void Popup(View view) {


        myDialog.setCancelable(false);
        TextView txtclose;
        myDialog.setContentView(R.layout.popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.show();

    }

    public void winorlose(String txt)
    {
        winDialog.setCancelable(false);
        TextView txtset;
        Button replaywin, mainmenuwin;
        winDialog.setContentView(R.layout.winorlose);
        txtset = winDialog.findViewById(R.id.textwin);
        replaywin = winDialog.findViewById(R.id.replaywin);
        mainmenuwin = winDialog.findViewById(R.id.mainmenuwin);

        txtset.setText(txt);

        replaywin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wi = new Intent(KoEnTouro.this,KoEnTouro.class);
                startActivity(wi);
            }
        });

        mainmenuwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(KoEnTouro.this,Cricenoyul.class);
                startActivity(main);
            }
        });

        winDialog.show();
    }
}
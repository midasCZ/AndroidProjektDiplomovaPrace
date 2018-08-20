package com.example.martik.kapky;


import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public int milliseconds = 10000;
    public  int skore = 0;
    TimerTask timerTaskAsync;
    Timer timerAsync;

    private void Start (){
        skore = 0;
        milliseconds = 10000;
        Skore(null);
        Cas(null);
        timerAsync = new Timer();
        timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        Game();
                    }
                });
            }
        };
        timerAsync.schedule(timerTaskAsync, 0, 1000);
    }


    public void btn_Dobra_Kapka_Clicked (View view){
    milliseconds = milliseconds+2000;
    skore = skore +1;
        Skore(null);
        Cas(null);

    }


    public void btn_Start_Clicked (View view){
    Start();
    Button button = findViewById(R.id.btn_Start);
    button.setEnabled(false);
    }

   private void Cas(View view) {
        TextView textView = findViewById(R.id.lbl_Cas);
        String string = Integer.toString(milliseconds/1000);
        textView.setText("Čas " + string);

    }

       private void Skore(View view){
        TextView textView = findViewById(R.id.lbl_Skore);
        String string = Integer.toString(skore);
        textView.setText("Skóre " + string);

    }

    private void Game()
    {
        milliseconds = milliseconds - 1000;
        //zjišťuje, jestli není konec, kdyžtak vykreslí čas a umístí tlačítko jinam, spustí opět časovač
        if (milliseconds > 0)
        {
            Cas(null);
            Skore(null);
            final FrameLayout layout = (FrameLayout) findViewById(R.id.frm);
            //ViewTreeObserver vto = layout.getViewTreeObserver();
            Random r = new Random();
            Button button = findViewById(R.id.btn_Dobra_Kapka);//herní tlačítko
            Button button1 = findViewById(R.id.btn_Start);// iniciované kvůli zjištění výšky ovládací lišty
            int x = r.nextInt(layout.getWidth() - button.getWidth() - button.getWidth() + 1) + button.getWidth();
            int y = r.nextInt(layout.getHeight() - button.getHeight() - button1.getHeight() - button.getHeight() + 1) + button.getHeight();
            button.setY(y);
            button.setX(x);
                }

        else
        {
            timerAsync.cancel();
            timerTaskAsync.cancel();
            Cas(null);
            Skore(null);

            Konec();
        }
    }



    private void Konec(){
        String string = Integer.toString(skore);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this );
        builder1.setMessage("Konec hry! Získal jsi " + skore + " bodů. Chceš si zahrát ještě jednou?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ano",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Start();
                    }
                });

        builder1.setNegativeButton(
                "Ne",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        System.exit(0);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void btn_About_Clicked (View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this );
        builder1.setMessage("Tato diplomová práce byla vytvořena Martinem Jodasem v rámci diplomové práce SROVÁNÍ VYBRANÝCH VÝVOJOVÝCH PROSTŘEDÍ PRO ANDROID SE ZAMĚŘENÍM NA VZDĚLÁVÁNÍ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ano",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });




        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}



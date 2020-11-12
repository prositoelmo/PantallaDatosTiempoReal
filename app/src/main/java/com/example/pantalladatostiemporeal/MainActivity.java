package com.example.pantalladatostiemporeal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable animationDrawable;
    TextView textoActual;
    Button iniciarCarreraBtn;
    ProgressBar avanceCarrera;
    Handler handler= new Handler();
    boolean isActivo= false;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mapBoton=findViewById(R.id.mapButton);
        ImageView runnerImg=findViewById(R.id.runnerImg);
        runnerImg.setBackgroundResource(R.drawable.image_run_list);
        iniciarCarreraBtn= findViewById(R.id.iniciarCarrera);
        avanceCarrera= findViewById(R.id.avanceCarrera);
        textoActual= findViewById(R.id.textoActual);
        animationDrawable= (AnimationDrawable) runnerImg.getBackground();
        avanceCarrera.setMax(100);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.iniciarCarrera:
                if(animationDrawable.isRunning()){
                    animationDrawable.stop();
                    iniciarCarreraBtn.setText("Iniciar Carrera");
                    isActivo=true;
                }
                else {
                    animationDrawable.start();
                    iniciarCarreraBtn.setText("Detener Carrera");
                    isActivo=false;
                }
                if(!isActivo){
                    Thread thread= new Thread(new Runnable() {

                        @Override
                        public void run() {
                            for (i=0;i<=100;i++){
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        textoActual.setText(i+"%");
                                        avanceCarrera.setProgress(i);

                                    }
                                });
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    thread.start();

                }
            break;
        }

    }
}
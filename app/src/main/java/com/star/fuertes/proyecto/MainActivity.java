package com.star.fuertes.proyecto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    Button boton;
   Button boton2;
  private MediaPlayer mediaPlayer;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.m);
        mediaPlayer.start();

        imageView=(ImageView) findViewById(R.id.imageView);
        boton=(Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,PlanetActivity.class);
                startActivity(i);
            }
        });//http://i5.photobucket.com/albums/y192/MasterTalon/star%20wars/Star_Wars_Logo.png
        Glide.with(this)
                .load("https://www.ilustra.org/wp-content/uploads/2015/12/giphy-145047293984kng.gif")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        boton2=(Button) findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SpeciesActivity.class);
                startActivity(i);
            }
        });/**/
    }
}

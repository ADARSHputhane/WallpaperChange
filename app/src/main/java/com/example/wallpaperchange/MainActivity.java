package com.example.wallpaperchange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
     Button changeButton;
    boolean running = false;
    int[] imagesArray = new int[]{
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
    };
    int i = 0;
    Timer time;
    TimerTask timerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeButton = findViewById(R.id.button_wallapaper);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running){
                     time = new Timer();
                    time.schedule(timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            changeWallPaper();
                        }
                    },0,1000);
                }else {
                    running = true;
                    changeWallPaper();
                }

            }
        });



    }




    private void changeWallPaper() {
        i = (i+1) % imagesArray.length;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imagesArray[i]);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(bitmap);
//            Toast.makeText(MainActivity.this, "Wallaper Changed", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
//            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}


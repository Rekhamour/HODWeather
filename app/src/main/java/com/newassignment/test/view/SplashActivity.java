package com.newassignment.test.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.newassignment.retrofitexample.R;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        progressBar.setVisibility(View.INVISIBLE);




        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    });

                    sleep(2000);


                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);


                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        thread.start();
    }


}

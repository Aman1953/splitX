package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);


        Thread td = new Thread(){

            public void run(){
                try{
                    sleep(2000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent5= new Intent(splashscreen.this,splashscreen2.class);
                    startActivity(intent5);
                    finish();
                }
            }

        };td.start();
    }
}
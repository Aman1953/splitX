package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen2);


        Thread td = new Thread(){

            public void run(){
                try{
                    sleep(1000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent2= new Intent(splashscreen2.this,login.class);
                    startActivity(intent2);
                    finish();
                }
            }

        };td.start();
    }
}
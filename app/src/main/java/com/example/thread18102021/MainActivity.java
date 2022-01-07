package com.example.thread18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                doSthing("A");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                doSthing("B");
            }
        });

        threadA.start();
        threadB.start();

    }

    private synchronized void doSthing(String message){
        for (int i = 0; i < 1000; i++) {
            Log.d("BBB",message + " : " + i);
        }
    }

}
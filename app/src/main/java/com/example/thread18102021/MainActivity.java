package com.example.thread18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Object objA , objB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objA = new Object();
        objB = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objA){
                    Log.d("BBB","Thread A is running");
                    try {
                        Thread.sleep(10);
                        synchronized (objB){
                            Log.d("BBB","hanlde object B");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objB){
                    Log.d("BBB","Thread B is running");
                    try {
                        Thread.sleep(10);
                        synchronized (objA){
                            Log.d("BBB","hanlde object A");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

        new CountDownTimer(2000,10){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Log.d("BBB","State Thread A " + threadA.getState());
                Log.d("BBB","State Thread B " + threadB.getState());
            }
        }.start();
    }

}
package com.example.thread18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    MyFlag myFlag;
    int a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFlag = new MyFlag(0);

        a = b = c = 0;

        // index = 0 => A sẽ chạy
        // index = 1 => B sẽ chạy
        // index = 2 => C sẽ chạy

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 100; ) {
                        if (myFlag.index == 0) {
                            a = i;
                            Log.d("BBB", "A : " + a);
                            myFlag.index = 1;
                            myFlag.notifyAll();
                            i++;
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 100; ) {
                        if (myFlag.index == 1) {
                            b = i;
                            Log.d("BBB", "B : " + b);
                            myFlag.index = 2;
                            myFlag.notifyAll();
                            i++;
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 100; ) {
                        if (myFlag.index == 2) {
                            c = a + b;
                            Log.d("BBB", "C : " + c);
                            myFlag.index = 0;
                            myFlag.notifyAll();
                            i++;
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        threadC.start();
        threadA.start();
        threadB.start();



    }

}
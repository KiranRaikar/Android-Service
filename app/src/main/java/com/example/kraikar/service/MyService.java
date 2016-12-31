package com.example.kraikar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand method invoked.");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    long futureTime = System.currentTimeMillis() + 5000;
                    while (System.currentTimeMillis() < futureTime) {
                        synchronized (this) {
                            try {
                                Log.i(TAG, "Something is cooking...");
                                wait(futureTime - System.currentTimeMillis());
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }
        };
        Thread myServiceThread = new Thread(r);
        myServiceThread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy method invoked.");
    }
}

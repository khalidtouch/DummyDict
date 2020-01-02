package com.example.dummydictionary.threading;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.dummydictionary.util.Constants;

import androidx.annotation.NonNull;

public class MyThread extends Thread {
    private static final String TAG = "MyThread";
    private Handler mMyThreadHandler = null;

    @Override
    public void run() {
        Looper.prepare();
        mMyThreadHandler = new Handler(Looper.myLooper());
        Looper.loop();

    }

    private class MyThreadHandler extends Handler{
        public MyThreadHandler(@NonNull Looper looper) {
            super(looper);
        }

     public void sendMessageToBackgroundThread(Message message){
            while(true){
                try{
                    mMyThreadHandler.sendMessage(message);
                    break;
                }catch (NullPointerException e){
                    Log.e(TAG, "sendMessageToBackgroundThread: null pointer: " + e.getMessage());
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e1){
                        e1.printStackTrace();
                    }
                }
            }
     }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case Constants
                        .WORD_INSERT_NEW:{
                    Log.d(TAG, "handleMessage: saving word on thread:" + Thread.currentThread().getName());
                    break;
                }

                case Constants.WORD_UPDATE:{
                    Log.d(TAG, "handleMessage: updating word on thread:" + Thread.currentThread().getName());

                    break;
                }

                case Constants.WORDS_RETRIEVE:{
                    Log.d(TAG, "handleMessage: retrieving words on thread: " + Thread.currentThread().getName());

                    break;
                }

                case Constants.WORD_DELETE:{
                    Log.d(TAG, "handleMessage: deleting word on thread: " + Thread.currentThread().getName());

                    break;
                }
            }
        }
    }
}

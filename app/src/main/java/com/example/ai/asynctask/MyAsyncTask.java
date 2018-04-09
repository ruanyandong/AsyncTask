package com.example.ai.asynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * AsyncTask注意事项：
 * 1、必须在UI线程中创建AsyncTask的实例
 * 2、必须在UI线程中调用AsyncTask的execute()方法
 * 3、重写的四个方法是系统自动调用的，不应手动调用
 * 4、每个AsyncTask只能被执行一次，多次调用将引发异常
 */

public class MyAsyncTask extends AsyncTask<String,Integer,Boolean> {

    public static final String TAG="MyAsyncTask";

    @Override
    protected Boolean doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: ");
        /**
         * publishProgress()方法被调用时,onProgressUpdate()方法才会被调用
         * 在这里可以传入一个进度值
         */
        publishProgress();
        return null;
    }

    

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.d(TAG, "onPostExecute: ");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: ");
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
        Log.d(TAG, "onCancelled: ");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d(TAG, "onCancelled: ");
    }
}

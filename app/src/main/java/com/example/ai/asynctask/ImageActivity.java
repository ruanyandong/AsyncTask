package com.example.ai.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

public class ImageActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private static final String URL =
            "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        initView();
        /**
         * execute()方法中传递进去的参数可以传递多个字符串，
         * 其实就是传递到doInBackground()这个方法里
         */
        new AsyncTaskImage().execute(URL);
    }

    private void initView() {
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

    }

    class AsyncTaskImage extends AsyncTask<String,Integer,Bitmap>{

        /**
         * 此方法在主线程中
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressBar.setVisibility(View.VISIBLE);
        }

        /**
         * 抽象类 URLConnection 是所有表示应用程序与 URL 之间通信链路的类的超类。
         * 该类的实例可以用来对由 URL 引用的资源进行读取和写入操作
         * 而HttpURLConnection支持 HTTP 特定功能的 URLConnection 他们之间除了继承，没有任何区别。
         * @param strings
         * @return
         */
        //此方法在子线程
        @Override
        protected Bitmap doInBackground(String... strings) {

            String url = strings[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try {

                connection = new URL(url).openConnection();
                is=connection.getInputStream();

                BufferedInputStream bis=new BufferedInputStream(is);

                Thread.sleep(3000);
                bitmap= BitmapFactory.decodeStream(bis);

                is.close();
                bis.close();
            }catch (Exception e){

            }

            return bitmap;
        }

        /**
         * 此方法在主线程中
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            mImageView.setImageBitmap(bitmap);
            mProgressBar.setVisibility(View.GONE);
        }
    }


}


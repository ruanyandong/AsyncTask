package com.example.ai.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.download_image)
    Button downloadImage;

    @BindView(R.id.progress_bar)
    Button progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);

        MyAsyncTask task = new MyAsyncTask();
        task.execute();
        //lamda表达式
        //Button mDownloadImage=findViewById(R.id.download_image);
        //mDownloadImage.setOnClickListener(v->{ });

        progressBar.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,
                    ProgressBarActivity.class);
            MainActivity.this.startActivity(intent);
        });
    }

    @OnClick(R.id.download_image)
    public void onViewClicked() {

        Intent intent = new Intent();
        intent.setClass(MainActivity.this,
                ImageActivity.class);
        this.startActivity(intent);

    }


}

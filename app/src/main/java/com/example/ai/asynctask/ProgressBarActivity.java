package com.example.ai.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressBarActivity extends AppCompatActivity {


    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private AsyncTaskProgressBar mAsyncTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progressbar);
        ButterKnife.bind(this);

        mAsyncTask=new AsyncTaskProgressBar();

        mAsyncTask.execute();

    }


    @Override
    protected void onPause() {
        super.onPause();
        /**
         * 在这里解决：for循环如果没有执行完就back,然后再进入，就会看不到进度条更新，因为之前的进度条没有执行完毕
         * 解决方法：让AsyncTask的生命周期与Activity保持一致
         */
       if(mAsyncTask!=null&&
               mAsyncTask.getStatus()==
               AsyncTask.Status.RUNNING){
           /**
            * cancel()方法只是将对应的AsyncTask标记为cancel状态，并不是真正地取消线程的执行
            */
           mAsyncTask.cancel(true);

       }

    }

    /**
     * AsyncTask底层是通过同步线程池实现的，所以执行任务是串行的，依次执行
     * 只有等前面的任务执行完毕，才能执行后面的任务
     */

    class AsyncTaskProgressBar extends AsyncTask<String,Integer,String>{


        @Override
        protected String doInBackground(String... strings) {
            /**
             * 模拟进度更新
             */
            /**
             * for循环如果没有执行完就back键,然后再进入，
             * 就会看不到进度条更新，因为之前的进度条没有执行完毕
             */
            for(int i=0;i<101;i++){

                /**
                 * 当在onPause方法里将对应的AsyncTask标记为cancel状态时，
                 * 这里就立即跳出循环，以此来解决：for循环如果没有执行完就back键,然后再进入，就会看不到进度条更新
                 */
                if(isCancelled()){
                    break;
                }

                /**
                 * publishProgress()方法可以同时传入多个int类型数值
                 */
                publishProgress(i);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(isCancelled()){
                return;
            }
            /**
             * values就是publishProgress()方法传入的多个int类型值，
             * 如果只传入一个，就是values[0],以此类推
             */
            //获取进度更新值
            mProgressBar.setProgress(values[0]);
        }



    }
}

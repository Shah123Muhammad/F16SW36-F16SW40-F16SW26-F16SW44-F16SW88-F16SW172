package com.example.hp.asynchtask;

import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button sleepBtn,cancelBtn;
    private SeekBar seekBar;
    private ProgressBar horizontalProgressBar;
    private ProgressBar circualrProgressBar;
    private TextView status_tv,percent_tv,seek_tv;

    private MyAsyncTaskClass onGoing = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sleepBtn = findViewById(R.id.sleepBtn);
        seekBar = findViewById(R.id.seekBar);
        horizontalProgressBar = findViewById(R.id.horizontalProgressBar);
        circualrProgressBar = findViewById(R.id.circularProgressBar);
        status_tv = findViewById(R.id.status_view);
        percent_tv = findViewById(R.id.percent_view);
        cancelBtn = findViewById(R.id.cancelBtn);
        seek_tv = findViewById(R.id.seekBarValueView);


        setOnClickOnSleepBtn();
        setOnClickOnCancelBtn();
        setOnSeekBarChangeEvent();
    }

    private void setOnClickOnCancelBtn(){
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoing.cancel(true);
                onGoing.onPostExecute("Cancled");
            }
        });

    }

    private void setOnClickOnSleepBtn(){
        sleepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = seekBar.getProgress();
                String message = "The Current Value in the seek bar is: "+ value;
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                onGoing =new MyAsyncTaskClass();
                onGoing.execute(value);
            }
        });

    }

    private void setOnSeekBarChangeEvent(){

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek_tv.setText(seekBar.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    private class MyAsyncTaskClass extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            circualrProgressBar.setVisibility(1);
            status_tv.setText("Work In Progress...");
        }


        @Override
        protected String doInBackground(Integer... integers) {
            int sec = 1;
            int sec_to_wait =seekBar.getProgress();

            int max_progress = 100;
            int incremented_by =(int) Math.ceil(max_progress / sec_to_wait) ;




            int progress = 0;
            try{
                for(int i=1;i<=integers[0];i++) {
                    Thread.sleep(1000 * sec);
                    progress+=incremented_by;
                    horizontalProgressBar.setProgress(progress);
                }
            }catch (InterruptedException ie){
                Toast.makeText(getApplicationContext(),ie.getMessage(),Toast.LENGTH_SHORT).show();
            }

            return "Completed";
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            circualrProgressBar.setVisibility(-1);
            //Used to ensure the progress bar to 100 percent.
            //Complete the progess bar status if it was missed by the Thread to full fill....
            horizontalProgressBar.setProgress(horizontalProgressBar.getMax());
            status_tv.setText(s);
        }
    }



}

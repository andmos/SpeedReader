package com.throwit.speedreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.*;


public class MainActivity extends Activity {


    public TextView myTextView;
    public String[] words;
    IFileBasedTextService textService;
    boolean isRunning;

    protected static final long TIME_DELAY = 205;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartSpeedReader("LoremIpsum.txt");

    }

    private void StartSpeedReader(String selectedFile) {

        textService = new TextFileReader();
        myTextView = (TextView) findViewById(R.id.wordTextField);

        TextHandler textHandler = new TextHandler(textService, getApplicationContext(),selectedFile);

        words = textHandler.getWordsFromFileText();

        this.placeTouchField();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
        private int counter = 0;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(isRunning && counter < words.length) {
                            myTextView.setText(words[counter]);
                            counter ++;
                        }
                    }
                });

                }
        };
        timer.schedule(timerTask, 0, TIME_DELAY);
    }

    public void placeTouchField(){
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.mainLayout);
        rlayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                isRunning = !isRunning;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

}

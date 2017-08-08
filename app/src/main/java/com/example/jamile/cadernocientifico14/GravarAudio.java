package com.example.jamile.cadernocientifico14;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class GravarAudio extends Activity {
    Button play,stop,record2;
    ImageButton gravar;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private Chronometer ch;
    private long milliseconds;
    private long millisecondsStop;
    private int apertei=0;
    TextView parar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravar_audio);
        parar= (TextView) findViewById(R.id.parar);
        //play=(Button)findViewById(R.id.button3);
        //stop=(Button)findViewById(R.id.button2);
        gravar= (ImageButton) findViewById(R.id.gp);

        //stop.setEnabled(false);
        //play.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";;

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parar.setText("PARAR");
                try {


                    // Log.i("I am here");
                    parar.setText("PARAR");
                    if(apertei==1){

                        Log.i("AAAA",String.valueOf(apertei));
                        myAudioRecorder.stop();
                        myAudioRecorder.release();
                        myAudioRecorder  = null;

                        millisecondsStop = System.currentTimeMillis();
                        milliseconds = SystemClock.elapsedRealtime() - ch.getBase();
                        ch.stop();
                        GravarAudio.this.finish();

                        gravar.setEnabled(false);
                    }
                    else{
                        ch=(Chronometer)findViewById(R.id.chronometer);
                        ch.setBase(SystemClock.elapsedRealtime() - (milliseconds + millisecondsStop));
                        ch.start();
                        Log.i("AAAA333",String.valueOf(apertei));
                        myAudioRecorder.prepare();
                        myAudioRecorder.start();
                    }
                    apertei=1;
                }

                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.i("AAAA1111",String.valueOf(apertei));
                //gravar.setEnabled(false);
                // stop.setEnabled(true);


                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myAudioRecorder.stop();
//                myAudioRecorder.release();
//                myAudioRecorder  = null;
//
//                stop.setEnabled(false);
//                play.setEnabled(true);
//
//                Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) throws IllegalArgumentException,SecurityException,IllegalStateException {
//                MediaPlayer m = new MediaPlayer();
//
//                try {
//                    m.setDataSource(outputFile);
//                }
//
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    m.prepare();
//                }
//
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                m.start();
//                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
//            }
//        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
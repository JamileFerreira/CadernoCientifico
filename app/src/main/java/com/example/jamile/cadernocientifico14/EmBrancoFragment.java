package com.example.jamile.cadernocientifico14;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import static android.view.View.GONE;

public class EmBrancoFragment extends Fragment {
    Button play, stop, record2;
    ImageButton gravar;
    private MediaRecorder myAudioRecorder;
    static String outputFile = null;
    private Chronometer ch;
    private long milliseconds;
    private long millisecondsStop;
    private int apertei = 0;
    TextView parar;
    static View view;
    int volta=0;
    public EmBrancoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        //setContentView(R.layout.activity_gravar_audio);
         view = inflater.inflate(R.layout.fragment_em_branco, container, false);

        parar = (TextView) view.findViewById(R.id.parar);
        //play=(Button)findViewById(R.id.button3);
        //stop=(Button)findViewById(R.id.button2);
        gravar = (ImageButton) view.findViewById(R.id.gp);
        ch = (Chronometer) view.findViewById(R.id.chronometer);
//        parar.setVisibility(GONE);
//        gravar.setVisibility(GONE);
//        ch.setVisibility(GONE);
        //stop.setEnabled(false);
        //play.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        view.setVisibility(GONE);



        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parar.setText("PARAR");
                try {


                    // Log.i("I am here");
                    parar.setText("PARAR");
                    if (apertei == 1) {
                        view.setVisibility(GONE);
                        Log.i("AAAA", String.valueOf(apertei));
                        myAudioRecorder.stop();
                        myAudioRecorder.release();
                        myAudioRecorder = null;

                        millisecondsStop = System.currentTimeMillis();
                        milliseconds = SystemClock.elapsedRealtime() - ch.getBase();
                        ch.stop();
                        ch = (Chronometer) view.findViewById(R.id.chronometer);
                        ch.setBase(SystemClock.elapsedRealtime());
                        parar.setText("GRAVAR");
                        apertei=0;
                        view.setVisibility(GONE);
                    } else if(apertei==0){view.setVisibility(GONE);
                        myAudioRecorder = new MediaRecorder();
                        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                        myAudioRecorder.setOutputFile(outputFile);

                        ch.setBase(SystemClock.elapsedRealtime());
                        ch.start();
                        Log.i("AAAA333", String.valueOf(apertei));
                        myAudioRecorder.prepare();
                        myAudioRecorder.start();
                        apertei = 1;
                    }

                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.i("AAAA1111", String.valueOf(apertei));
            }
        });
        return view;
    }
}

package com.example.audiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

// for converting audio file
import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;

    private String audioFilePath;
    private Button stopButton;
    private Button playButton;
    private Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordButton = (Button) findViewById(R.id.recordButton);
        playButton = (Button) findViewById(R.id.playbackButton);
        stopButton = (Button) findViewById(R.id.stoprecordButton);

        playButton.setEnabled(false);
        stopButton.setEnabled(false);
        audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myaudio.3gp";
    }

    public void recordAudio(View view) throws IOException {
        // record audio

        stopButton.setEnabled(true);
        playButton.setEnabled(false);
        recordButton.setEnabled(false);

        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        // increases quality of audio (hopefully)
        mediaRecorder.setAudioEncodingBitRate(16);

        mediaRecorder.setOutputFile(audioFilePath);
        mediaRecorder.prepare();
        mediaRecorder.start();
    }

    public void stopAudio(View view) {
        // stops the audio from recording

        stopButton.setEnabled(false);
        recordButton.setEnabled(true);
        playButton.setEnabled(true);

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public void playAudio(View view) throws IOException {
        // plays back the most recent recorded audio

        playButton.setEnabled(false);
        recordButton.setEnabled(true);
        stopButton.setEnabled(true);

        MediaPlayer m = new MediaPlayer();
        m.setDataSource(audioFilePath);
        m.prepare();
        m.start();
    }
}

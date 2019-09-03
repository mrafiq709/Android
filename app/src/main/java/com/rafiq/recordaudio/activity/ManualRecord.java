package com.rafiq.recordaudio.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.coremedia.iso.IsoFile;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Movie;
import android.os.Build;
import android.os.Environment;
import android.provider.CallLog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AACTrackImpl;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.rafiq.recordaudio.R;
import com.rafiq.recordaudio.Utility.AllKeys;
import com.rafiq.recordaudio.services.CallRecordService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.rafiq.recordaudio.Utility.RunTimePermission.hasPermissions;
import static com.rafiq.recordaudio.Utility.Utils.log;

public class ManualRecord extends AppCompatActivity implements View.OnClickListener {

    TextView recordTime;
    ImageButton recordAndPause, stopAndList;
    Button mergeButton;
    private int cnt = 1;
    private FFmpeg ffmpeg;

    String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        loadFFMpegBinary();
        recordAndPause.setOnClickListener(this);
        stopAndList.setOnClickListener(this);
        mergeButton.setOnClickListener(this);

    }

    private void init() {
        setContentView(R.layout.activity_manual_record);
        recordAndPause = findViewById(R.id.recordAndPause);
        stopAndList = findViewById(R.id.stopAndList);
        recordTime = findViewById(R.id.recordingTime);
        mergeButton = findViewById(R.id.mergeAudio);

    }

    private AlphaAnimation alphaAnimation;
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.recordAndPause:
                if (cnt % 2 != 0){

                    timer();

                    startService(this, new Intent());

                    recordAndPause.setImageResource(R.drawable.pause);
                    stopAndList.setImageResource(R.drawable.stop);

                    fadeAnimationStop(recordTime);

                }else {
                    // When Pause
                    recordAndPause.setImageResource(R.drawable.play);
                    t.cancel();
                    this.stopService(new Intent(this, CallRecordService.class));

                    fadeAnimationStart(recordTime);
                }

                cnt++;
                cnt %= 2;
                break;
            case R.id.stopAndList:
                t.cancel();

                this.stopService(new Intent(this, CallRecordService.class));

                fadeAnimationStop(recordTime);

                minute = 0;
                seconds = 0;
                hour = 0;
                cnt = 1;

                recordTime.setText("00 : 00 : 00");
                stopAndList.setImageResource(R.drawable.list);
                recordAndPause.setImageResource(R.drawable.play);

                String[] source = new String[100];
                int k = 0;

                String filePath = Environment.getExternalStorageDirectory().getPath();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                filePath += "/" + AllKeys.RECORD_DIRECTORY_NAME +"/"+date+"/";

                File folder = new File(filePath);
                File[] listOfFiles = folder.listFiles();

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {

                        String str = listOfFiles[i].getName();
                        if (str.contains("Record")){
                            source[k++] = filePath + str;
                            log(str);
                        }
                    } else if (listOfFiles[i].isDirectory()) {
                        log("Directory " + listOfFiles[i].getName());
                    }
                }

                mergeMediaFiles(source, filePath);

                break;

            case R.id.mergeAudio:
                mergeAudio();
        }
    }

    private void mergeAudio() {
        File moviesDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MUSIC
        );

        String filePrefix = "merge";
        String fileExtn = ".mp3";

        String filePath = Environment.getExternalStorageDirectory().getPath();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        filePath += "/" + AllKeys.RECORD_DIRECTORY_NAME +"/"+date+"/";
        String audioRealPath = filePath;

        File dest = new File(moviesDir, filePrefix + fileExtn);

        int fileNo = 0;
        while (dest.exists()) {
            fileNo++;
            dest = new File(moviesDir, filePrefix + fileNo + fileExtn);
        }

        String audio1 = filePath + "Record_032948.aac";
        String audio2 = filePath + "Record_032955.aac";

        log("src: audio:" + audioRealPath);
        mFilePath = dest.getAbsolutePath();

        final String[] complexCommand = {"ffmpeg -i", audio1, "-i", audio2, "-filter_complex amerge -c:a libmp3lame -q:a 4", mFilePath};

        execFFmpegBinary(complexCommand);
    }

    /**
     * Load FFmpeg binary
     */
    private void loadFFMpegBinary() {
        try {
            if (ffmpeg == null) {
                log("ffmpeg : era nulo");
                ffmpeg = FFmpeg.getInstance(this);
            }
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    showUnsupportedExceptionDialog();
                }

                @Override
                public void onSuccess() {
                    log("ffmpeg : correct Loaded");
                }
            });
        } catch (FFmpegNotSupportedException e) {
            showUnsupportedExceptionDialog();
        } catch (Exception e) {
            log("EXception no controlada : " + e);
        }
    }

    private void showUnsupportedExceptionDialog() {
        new AlertDialog.Builder(ManualRecord.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Not Supported")
                .setMessage("Device Not Supported")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ManualRecord.this.finish();
                    }
                })
                .create()
                .show();

    }

    /**
     * Executing ffmpeg binary
     */
    private void execFFmpegBinary(final String[] command) {
        try {
            ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
                @Override
                public void onFailure(String s) {
                    log("FAILED with output : " + s);
                }

                @Override
                public void onSuccess(String s) {
                    log("SUCCESS with output : " + s);
                    /*Intent intent = new Intent(AudioVideoMergeActivity.this, VideoPreviewActivity.class);
                    intent.putExtra(FILEPATH, filePath);
                    startActivity(intent);*/
                }

                @Override
                public void onProgress(String s) {
                    log("Started command : ffmpeg " + Arrays.toString(command));
                    //progressDialog.setMessage("progress : " + s);
                    log("progress : " + s);
                }

                @Override
                public void onStart() {
                    log("Started command : ffmpeg " + Arrays.toString(command));
                    /*progressDialog.setMessage("Processing...");
                    progressDialog.show();*/
                }

                @Override
                public void onFinish() {
                    log("Finished command : ffmpeg " + Arrays.toString(command));
                    //progressDialog.dismiss();

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            // do nothing for now
        }
    }

    private void fadeAnimationStop(TextView textView) {

        if (alphaAnimation != null){
            textView.clearAnimation();
            alphaAnimation.cancel();
            alphaAnimation.reset();
        }
    }

    private void fadeAnimationStart(TextView textView) {

        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(Animation.INFINITE);

        textView.startAnimation(alphaAnimation);
    }

    int minute = 0, seconds = 0, hour = 0;
    private Timer t;
    public void timer(){

        t = new Timer("hello", true);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                recordTime.post(new Runnable() {
                    @Override
                    public void run() {
                        seconds++;
                        if (seconds == 60) {
                            seconds = 0;
                            minute++;
                        }
                        if (minute == 60) {
                            minute = 0;
                            hour++;
                        }
                        recordTime.setText(""
                                + (hour > 9 ? hour : ("0" + hour)) + " : "
                                + (minute > 9 ? minute : ("0" + minute))
                                + " : "
                                + (seconds > 9 ? seconds : "0" + seconds));
                    }
                });
            }
        },1000,1000);

    }

    private void startService(Context context, Intent intent) {
        String[] permissions = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };

        if (hasPermissions(context, permissions)){
            intent.setClass(context, CallRecordService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                context.startForegroundService(intent);
            }else{
                context.startService(intent);
            }

        }else{
            log("Required permission is missing");
        }
    }

    private boolean mergeMediaFiles(String[] sourceFiles, String targetFile) {
        try {

            Movie movie = new Movie();

            for (String filename : sourceFiles) {
                log(filename);
                if (filename != null){
                    AACTrackImpl aacTrack = new AACTrackImpl(new FileDataSourceImpl(filename));
                    movie.addTrack(aacTrack);
                }
            }

            Container mp4file = new DefaultMp4Builder().build(movie);
            File file = new File(targetFile);

            if (!file.exists()){
                file.mkdir();
            }

            FileChannel fc = new FileOutputStream(file + "/output.mp3").getChannel();
            mp4file.writeContainer(fc);
            fc.close();
            return true;
        }
        catch (IOException e) {
            log("Error merging media files. exception: "+e.getMessage());
            return false;
        }
    }
}

package com.rafiq.recordaudio.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.rafiq.recordaudio.R;
import com.rafiq.recordaudio.Utility.AllKeys;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.rafiq.recordaudio.Utility.Utils.log;

public class CallRecordService extends Service {

    private MediaRecorder recorder;
    private NotificationManagerCompat notificationManager;
    private String[] sourceFile;
    private String targetFile;

    public CallRecordService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startRecording();

        return START_STICKY;
    }

    private void startRecording() {

        if (recorder != null) {
            // recorder already running
            return;
        }

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFile(getFileName());

        try {
            recorder.prepare();
            log("Recording start");

            recorder.start();
            showNotification();

        }catch (IOException e){
            log("Prepare() failed: " + e);
            e.printStackTrace();
        }
    }

    private void showNotification() {
        String notificationTitle = "Recording";

        // For android oreo or higher we need to start notification in foreground
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(AllKeys.CHANNEL_ID,
                    notificationTitle,
                    NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, AllKeys.CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(AllKeys.NOTIFICATION_ID, notification);

        }else{
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, AllKeys.CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notificationTitle)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setOngoing(true);

            notificationManager = NotificationManagerCompat.from(this);

            notificationManager.notify(AllKeys.NOTIFICATION_ID, builder.build());

        }
    }

    private String getFileName() {

        String filePath = Environment.getExternalStorageDirectory().getPath();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        filePath += "/" + AllKeys.RECORD_DIRECTORY_NAME +"/"+date+"/";

        File file = new File(filePath);

        // When file is not already exist
        if (!file.exists()){
            // Make a new directory with current file name
            file.mkdirs();

            // Create no media file
            createNoMedia(file.getAbsolutePath());
        }

        String time = new SimpleDateFormat("hhmmss").format(new Date());

        // Audio save location
        String audioPath = file.getAbsolutePath()+"/Record_"+ time + AllKeys.AUDIO_EXTENSION;

        return audioPath;
    }

    private void createNoMedia(String path) {
        File file = new File(path+"/"+".nomedia");
        try {
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
            log("Failed to create noMedia file");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRecording();
    }

    /**
     * App is closing, so stop recorder
     */
    private void stopRecording() {
        if(recorder != null) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
        }
        log("recording stopped");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            stopForeground(true);
        }else{
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(AllKeys.NOTIFICATION_ID);
        }
    }
}

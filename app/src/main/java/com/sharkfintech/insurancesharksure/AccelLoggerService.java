package com.sharkfintech.insurancesharksure;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Albert Zhang on 30/7/2016.
 */
public class AccelLoggerService extends Service implements SensorEventListener {
    private static final String DEBUG_TAG = "AccelLoggerService";

    private SensorManager sensorManager = null;
    private Sensor sensor = null;

    private ArrayList<String> timeValues = new ArrayList<String>();
    private ArrayList<String> accelValues = new ArrayList<String>();

    long time = System.currentTimeMillis();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("s", "init");
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder( 1,
                new ComponentName( getPackageName(),
                        UploaderJobSchedule.class.getName() ) );
<<<<<<< HEAD
        builder.setPeriodic(5000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
=======
        builder.setPeriodic(60000);
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
>>>>>>> f80e26e806ccc8e06e31b86af8b406c5e3cd13df
        if( jobScheduler.schedule( builder.build() ) <= 0 ) {
            Log.i("s", "gg buddy");
        }else{
            Log.i("s", "start");
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // grab the values and timestamp
        new SensorEventLoggerTask().execute(event);
        // stop the sensor and service
        sensorManager.unregisterListener(this);
        stopSelf();
    }

    private class SensorEventLoggerTask extends AsyncTask<SensorEvent, Void, Void> {
        @Override
        protected Void doInBackground(SensorEvent... events) {
            SensorEvent event = events[0];
            // log the value
            timeValues.add((System.currentTimeMillis() - time) / 1000f + "");
            accelValues.add(event.values[0] + "");
            return null;
        }

    }
    private class UploaderJobSchedule extends JobService {
        private Handler jobHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                String[] timeArray = timeValues.toArray(new String[] {});
                String[] accelArray = accelValues.toArray(new String[] {});
                String csv = "";
                for(int i = 0; i < timeArray.length; i++){
                    csv += timeArray + ",";
                    csv += accelArray + ",";
                }
                DataUploader uploader = new DataUploader("jerkymotion", "accel", csv);
                uploader.post();
                jobFinished((JobParameters) msg.obj, false);
                Log.i("hi","Anything");
                return false;
            }
        });
        @Override
        public boolean onStartJob(JobParameters params) {
            jobHandler.sendMessage(Message.obtain(jobHandler, 1, params));
            return true;
        }

        @Override
        public boolean onStopJob(JobParameters params) {
            jobHandler.removeMessages(1);
            return false;
        }

    }

}

package com.sharkfintech.insurancesharksure;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;

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

}

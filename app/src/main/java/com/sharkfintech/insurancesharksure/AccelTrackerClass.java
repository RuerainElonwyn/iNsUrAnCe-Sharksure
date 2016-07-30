package com.sharkfintech.insurancesharksure;

/**
 * Created by Albert Zhang on 30/7/2016.
 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class AccelTrackerClass extends Activity implements SensorEventListener {

    private static final int X_AXIS_INDEX = 0;
    private static final int Y_AXIS_INDEX = 1;
    private static final int Z_AXIS_INDEX = 2;

    int counter;
    int i=0;

    public String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";
    //public String fileDocumentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/AccelArchwizard_trackerData";

    private File file;
    private ArrayList<String> timeValues = new ArrayList<String>();
    private ArrayList<String> accelValues = new ArrayList<String>();
    private ArrayList<String> saveValues = new ArrayList<String>();

    private static final Random RANDOM = new Random();
    private int lastX = 0;

    private SensorManager mSensorManager;
    private Sensor mSensor;


    long time = System.currentTimeMillis();


    private int framesCount = 0;
    private int currentAxisIndex = X_AXIS_INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        counter = 0;

        File directory = new File(filePath);
        if (!directory.exists()){
            directory.mkdir();
        }

        //File directoryDocuments = new File(fileDocumentPath);
        //if (!directoryDocuments.exists()){
        //    directoryDocuments.mkdir();
        //}


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        //File stuff here


        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateNow = new Date();

        //System.out.println(df.format(dateNow));



    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        double total;
        //mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }*/

    // add random data to graph, for testing purposes
    private void addRandomEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        //series.appendData(new DataPoint(lastX++, RANDOM.nextDouble() * 10d), true, 500);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //addRandomEntry();

        // get the new point based on the readings from the accelerometer
        //DataPoint point = new DataPoint(this.framesCount++, event.values[this.currentAxisIndex]);

        //float x = event.values[0];

        // add the point to a collection of all the points that should be visible on the screen
        //this.seismicActivityBuffer.add(point);

        // draw the chart with all the points that should be visible*
        //this.chart = createChart(seismicActivityBuffer);

        /*Intent dataBroadcastTime = new Intent();
        dataBroadcastTime.putExtra("time", System.currentTimeMillis());
        sendBroadcast(dataBroadcastTime);

        Intent dataBroadcastAccelX = new Intent();
        dataBroadcastAccelX.putExtra("accel", event.values[0]);
        sendBroadcast(dataBroadcastAccelX);

        Intent dataBroadcastPoint = new Intent();
        dataBroadcastPoint.putExtra("point", point);*/

        // keep the point in another collection, for historic purposes
        //this.allSeismicActivity.add(point);
        if(i%10 == 0)
        {
            i=i-10;
            timeValues.add((System.currentTimeMillis() - time) / 1000f + "");
            accelValues.add(event.values[0] + "");
        }
        i++;

        /*saveValues.add((System.currentTimeMillis() - time) / 1000f + "");
        saveValues.add(event.values[0] + "");*/

        //TextView update = (TextView) findViewById(R.id.textViewAccel);
        //update.setText("Current Acceleration: " + event.values[0] + " m/s^2");

        //timeValues.add((System.currentTimeMillis() - time) / 1000f + "");

        //Log.i("ACCEL VALUE", event.values[0] + "");
        //Log.e("ARRAYLIST_ACCEL_VALUE", accelValues.get(accelValues.size() - 1));
        //Log.e("ARRAYLIST_TIME_VALUE", timeValues.get(timeValues.size() - 1));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing here.
    }

    public void onSaveClick(View v) {//TODO CALL THIS ONCE PER HOUR

        //Button button = (Button) findViewById(R.id.saveButton);
        //button.setEnabled(false);

        String[] timeArray = timeValues.toArray(new String[] {});

        String[] accelArray = accelValues.toArray(new String[] {});

        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
            Date dateNow = new Date();

            file = new File(filePath + File.separator + "Acceleration_data_" + df.format(dateNow) + ".csv");

            if(file.getParentFile().mkdirs()){
                file.createNewFile();
            }

            /*if (!file.exists()){
                file.mkdir();
            }*/

            //file.createNewFile();

            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < timeArray.length; i++){
                //writer.append(timeValues.get(i) + ",");
                writer.append(timeArray[i] + ",");
                //writer.append(accelValues.get(i) + ",");
                writer.append(accelArray[i] + ",");
            }
            writer.flush();
            writer.close();
            Toast.makeText(getApplicationContext(), "Data uploaded as " + file.getName(), Toast.LENGTH_LONG).show();
            //TODO UPLOAD CODE HERE
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

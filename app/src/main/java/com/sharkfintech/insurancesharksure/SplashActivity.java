package com.sharkfintech.insurancesharksure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Albert Zhang on 11/4/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainSharksafe.class);
        startActivity(intent);
        finish();
    }
}

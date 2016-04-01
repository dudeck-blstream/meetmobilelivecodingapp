package com.blstream.kameleon;

import com.blstream.kameleon.cache.BeaconCache;
import com.blstream.kameleon.model.BeaconItem;
import com.estimote.sdk.MacAddress;
import com.estimote.sdk.cloud.CloudCallback;
import com.estimote.sdk.cloud.EstimoteCloud;
import com.estimote.sdk.cloud.model.BeaconInfo;
import com.estimote.sdk.exception.EstimoteServerException;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;



public class SplashScreenActivity extends AppCompatActivity implements CloudCallback<BeaconInfo> {

    private static final MacAddress[] competetionMacs = new MacAddress[] {
            MacAddress.fromString("E3:1F:1E:92:43:B8"),
            MacAddress.fromString("C2:1D:2D:80:24:59"),
            MacAddress.fromString("F5:31:AE:01:97:52")
    };

    private int counter;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BeaconCache.getInstance().getBeacons().clear();
        downloadBeaconsData();
    }

    private void downloadBeaconsData() {
        EstimoteCloud.getInstance().fetchBeaconDetails(competetionMacs[counter], this);
    }

    @Override
    public void success(final BeaconInfo beaconInfo) {
        BeaconCache instance = BeaconCache.getInstance();
        instance.addBeacon(new BeaconItem(beaconInfo));
        onResponse();
    }

    @Override
    public void failure(final EstimoteServerException e) {
        Toast.makeText(this, "Beacon info download failure", Toast.LENGTH_SHORT).show();
        onResponse();
    }

    private void onResponse() {
        counter++;
        if (counter >= competetionMacs.length) {
            startMainActivity();
        } else {
            downloadBeaconsData();
        }
    }

    private void startMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

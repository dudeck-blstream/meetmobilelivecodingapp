package com.blstream.kameleon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blstream.kameleon.cache.BeaconCache;
import com.blstream.kameleon.model.BeaconItem;
import com.estimote.sdk.MacAddress;
import com.estimote.sdk.cloud.CloudCallback;
import com.estimote.sdk.cloud.EstimoteCloud;
import com.estimote.sdk.cloud.model.BeaconInfo;
import com.estimote.sdk.exception.EstimoteServerException;



public class SplashScreenActivity extends AppCompatActivity implements CloudCallback<BeaconInfo> {

    private static final MacAddress[] competetionMacs = new MacAddress[]{
            MacAddress.fromString("E3:1F:1E:92:43:B8"),
            MacAddress.fromString("C2:1D:2D:80:24:59"),
            MacAddress.fromString("F5:31:AE:01:97:52")
    };
    private int counter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadBeaconsData();
    }

    private void downloadBeaconsData() {
        EstimoteCloud.getInstance().fetchBeaconDetails(competetionMacs[counter], this);
    }

    @Override
    public void success(BeaconInfo beaconInfo) {
        BeaconCache.getInstance().addBeacon((BeaconItem)beaconInfo);
        if(counter >= competetionMacs.length){
            startMainActivity();
        } else {
            counter ++;
            downloadBeaconsData();
        }
    }

    @Override
    public void failure(EstimoteServerException e) {
        Toast.makeText(this, "Beacon info download failure", Toast.LENGTH_SHORT).show();
        if(counter >= competetionMacs.length){
            startMainActivity();
        } else {
            downloadBeaconsData();
        }
        counter ++;
    }

    private void startMainActivity() {
    }
}

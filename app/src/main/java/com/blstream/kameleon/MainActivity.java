package com.blstream.kameleon;

import com.blstream.kameleon.cache.BeaconCache;
import com.blstream.kameleon.model.BeaconItem;
import com.blstream.kameleon.util.BeaconUtils;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private BeaconManager beaconManager;

    @Bind(R.id.progress)
    View progressView;

    @Bind(R.id.beacon1)
    ImageView beaconBlue;

    @Bind(R.id.beacon2)
    ImageView beacon2;

    @Bind(R.id.beacon3)
    ImageView beacon3;

    private ImageView[] beacons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupBeaconsIcon();

        setupBeaconManager();
    }

    private void setupBeaconsIcon() {
        beacons = new ImageView[] {beaconBlue, beacon2, beacon3};
    }

    private void setupBeaconManager() {
        beaconManager = new BeaconManager(this);
        beaconManager.connect(new MyServiceReadyCallback());
        beaconManager.setRangingListener(new MyRangingListener());
        beaconManager.setMonitoringListener(new MyMonitoringListener());
    }

    private void updateBeaconData(Iterable<Beacon> list) {

        for (Beacon beacon : list) {
            BeaconItem beaconByMinor = BeaconCache.getInstance().findBeaconByMinor(beacon.getMinor());
            if (beaconByMinor != null) {
                if (!beaconByMinor.isDiscovered()) {
                    double accuracy = Utils.computeAccuracy(beacon);
                    beaconByMinor.setAccuracy(accuracy);

                    if (accuracy < BeaconUtils.DISCOVER_MIN_VALUE) {
                        beaconByMinor.setDiscovered(true);
                    }
                }
            }
        }
        refreshUI();
    }

    private void refreshUI() {
        for (final BeaconItem beaconItem : BeaconCache.getInstance().getBeacons()) {




        }


        //        Drawable discoveredDrawable = ContextCompat.getDrawable(this, R.drawable.ic_action_done);
        //        BeaconItem testBeaconBlue = testBeacons.getByName("Icy Marshmallow");
        //        //TODO: list all beacons?!
        //        if (testBeaconBlue != null && testBeaconBlue.isDiscovered()) {
        //            beaconBlue.setText(getString(R.string.beacon_discovered));
        //            beaconBlue.setCompoundDrawablesWithIntrinsicBounds(discoveredDrawable, null, null, null);
        //            beaconBlue.setBackgroundColor(testBeaconBlue.getColor());
        //        } else {
        //            beaconBlue.setText(getString(R.string.beacon_info_pattern,
        //                    BeaconList.BEACON_BLUE,
        //                    testBeaconBlue.getAccuracy()));
        //            int colorWithAlpha = BeaconUtils.adjustAlpha(testBeaconBlue);
        //            beaconBlue.setBackgroundColor(colorWithAlpha);
        //        }
    }

    private void sendEmail() {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", getString(R.string.bls_email_address), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MeetMobile 5/4/2016 contest");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Am I the winner?");

        startActivity(Intent.createChooser(emailIntent, "Choose your email app"));
    }

    @Override
    protected void onDestroy() {
        beaconManager.disconnect();
        super.onDestroy();
    }

    private class MyRangingListener implements BeaconManager.RangingListener {

        @Override
        public void onBeaconsDiscovered(Region region, List<Beacon> list) {
            if (!list.isEmpty()) {
                BeaconCache beaconCache = BeaconCache.getInstance();
                if (beaconCache.areAllDiscovered()) {
                    sendEmail();
                    beaconManager.stopRanging(beaconCache.getRegion());
                    beaconManager.stopMonitoring(beaconCache.getRegion());
                } else {
                    updateBeaconData(list);
                }
            }
        }
    }

    private class MyMonitoringListener implements BeaconManager.MonitoringListener {

        @Override
        public void onEnteredRegion(Region region, List<Beacon> list) {
            updateBeaconData(list);
            progressView.setVisibility(View.GONE);
        }

        @Override
        public void onExitedRegion(Region region) {
            progressView.setVisibility(View.VISIBLE);
        }
    }

    private class MyServiceReadyCallback implements BeaconManager.ServiceReadyCallback {

        @Override
        public void onServiceReady() {
            BeaconCache beaconCache = BeaconCache.getInstance();
            beaconManager.startRanging(beaconCache.getRegion());
            beaconManager.startMonitoring(beaconCache.getRegion());
        }
    }
}

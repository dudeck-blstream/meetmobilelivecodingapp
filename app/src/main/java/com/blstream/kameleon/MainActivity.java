package com.blstream.kameleon;

import com.blstream.kameleon.cache.BeaconCache;
import com.blstream.kameleon.model.BeaconItem;
import com.blstream.kameleon.util.BeaconUtils;
import com.blstream.kameleon.util.EmailUtil;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.Utils;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private BeaconManager beaconManager;

    @Bind(R.id.progress)
    View progressView;

    @Bind(R.id.main_container)
    View container;

    Map<BeaconItem, BeaconViewHolder> viewMap = new HashMap<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final ViewGroup container = (ViewGroup) findViewById(R.id.beacon_container);

        setupBeaconViewMap(container);
        setupBeaconManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }


    private void setupBeaconViewMap(final ViewGroup view) {
        final List<BeaconItem> beacons = getBeacons();
        for (final BeaconItem beacon : beacons) {
            final BeaconViewHolder beaconViewHolder = new BeaconViewHolder(view);
            beaconViewHolder.setColor(BeaconUtils.getColor(beacon));
            viewMap.put(beacon, beaconViewHolder);
        }
    }

    private void setupBeaconManager() {
        beaconManager = new BeaconManager(this);
        beaconManager.connect(new MyServiceReadyCallback());
        beaconManager.setMonitoringListener(new MyMonitoringListener());
        beaconManager.setRangingListener(new MyRangingListener());
    }

    private void updateBeaconData(final Iterable<Beacon> list) {
        for (final Beacon beacon : list) {
            final BeaconItem beaconByMinor = BeaconCache.getInstance().findBeaconByMinor(beacon.getMinor());
            if (beaconByMinor != null) {
                if (!beaconByMinor.isDiscovered()) {
                    final double accuracy = Utils.computeAccuracy(beacon);
                    beaconByMinor.setAccuracy(accuracy);

                    if (accuracy < BeaconUtils.DISCOVER_MIN_VALUE) {
                        beaconByMinor.setDiscovered(true);
                    }
                }
            }
        }
        updateUI();
    }

    private void updateUI() {
        final List<BeaconItem> beacons = getBeacons();
        for (final BeaconItem beacon : beacons) {
            final BeaconViewHolder beaconViewHolder = viewMap.get(beacon);
            beaconViewHolder.setAccuracy(beacon.getAccuracy());
            beaconViewHolder.setDiscovered(beacon.isDiscovered());
        }
    }

    private List<BeaconItem> getBeacons() {
        return BeaconCache.getInstance().getBeacons();
    }

    @Override
    protected void onDestroy() {
        beaconManager.disconnect();
        super.onDestroy();
    }

    private class MyServiceReadyCallback implements BeaconManager.ServiceReadyCallback {

        @Override
        public void onServiceReady() {
            beaconManager.startRanging(BeaconCache.getRegion());
            beaconManager.startMonitoring(BeaconCache.getRegion());
        }
    }

    private class MyMonitoringListener implements BeaconManager.MonitoringListener {

        @Override
        public void onEnteredRegion(final Region region, final List<Beacon> list) {
            updateBeaconData(list);
            progressView.setVisibility(View.GONE);
        }

        @Override
        public void onExitedRegion(final Region region) {
            progressView.setVisibility(View.VISIBLE);
        }
    }

    private class MyRangingListener implements BeaconManager.RangingListener {
        @Override
        public void onBeaconsDiscovered(final Region region, final List<Beacon> list) {
            if (!list.isEmpty()) {
                final BeaconCache beaconCache = BeaconCache.getInstance();
                if (beaconCache.areAllDiscovered()) {
                    beaconManager.stopRanging(BeaconCache.getRegion());
                    beaconManager.stopMonitoring(BeaconCache.getRegion());

                    showSuccessMessage();
                } else {
                    updateBeaconData(list);
                }
            }
        }

        private void showSuccessMessage() {
            Snackbar.make(container, "Sprawdź czy wygrałeś!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Wyślij", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EmailUtil.sendEmail(MainActivity.this);
                        }
                    }).show();
        }
    }
}

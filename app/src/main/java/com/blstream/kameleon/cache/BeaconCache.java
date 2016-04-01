package com.blstream.kameleon.cache;

import com.blstream.kameleon.model.BeaconItem;
import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.List;

public class BeaconCache {
    private static final String REGION_NAME = "TestRegion";

    private static volatile BeaconCache ourInstance = new BeaconCache();

    private List<BeaconItem> beacons = new ArrayList<>();

    public static BeaconCache getInstance() {
        return ourInstance;
    }

    private BeaconCache() {
    }

    public static Region getRegion() {
        return new Region(REGION_NAME, null, null, null);
    }

    public List<BeaconItem> getBeacons() {
        return beacons;
    }

    public void addBeacon(final BeaconItem beaconInfo){
        beacons.add(beaconInfo);
    }

    public BeaconItem findBeaconByMinor(final int minor) {
        for (final BeaconItem beacon : beacons) {
            if (beacon.getBeaconInfo().minor == minor) {
                return beacon;
            }
        }
        return null;
    }

    public boolean areAllDiscovered() {
        for (final BeaconItem beacon : beacons) {
            if (!beacon.isDiscovered()) {
                return false;
            }
        }
        return true;
    }
}

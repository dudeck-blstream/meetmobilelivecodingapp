package com.blstream.kameleon.cache;

import com.blstream.kameleon.model.BeaconItem;

import java.util.ArrayList;
import java.util.List;

public class BeaconCache {
    private static BeaconCache ourInstance = new BeaconCache();
    private List<BeaconItem> beacons = new ArrayList<>();

    public static BeaconCache getInstance() {
        return ourInstance;
    }

    private BeaconCache() {
    }

    public List<BeaconItem> getBeacons() {
        return beacons;
    }

    public void addBeacon(BeaconItem beaconInfo){
        beacons.add(beaconInfo);
    }
}

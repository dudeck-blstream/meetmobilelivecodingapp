package com.blstream.kameleon.cache;

import com.blstream.kameleon.model.BeaconId;
import com.blstream.kameleon.model.CompetentationBeacons;
import com.estimote.sdk.cloud.model.BeaconInfo;

import java.util.ArrayList;
import java.util.List;

public class BeaconCache {
    private static BeaconCache ourInstance = new BeaconCache();
    private List<BeaconInfo> beacons = new ArrayList<>();

    public static BeaconCache getInstance() {
        return ourInstance;
    }

    private BeaconCache() {
    }

    public List<BeaconInfo> getBeacons() {
        return beacons;
    }

    public void addBeacon(BeaconInfo beaconInfo){
        beacons.add(beaconInfo);
    }
}

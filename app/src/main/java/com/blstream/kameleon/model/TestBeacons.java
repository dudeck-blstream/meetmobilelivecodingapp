package com.blstream.kameleon.model;

import android.graphics.Color;

import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.Objects;

public class TestBeacons extends ArrayList<TestBeacon> {

    //test beacons names
    public static final String BEACON_BLUE = "BLUE";
    private static final String REGION_NAME = "TestRegion";

    public TestBeacons() {
        add(new TestBeacon("b9407f30-f5f8-466e-aff9-25556b57fe6d", 44545, 38738, Color.BLUE, BEACON_BLUE));
    }

    public static Region getRegion() {
        return new Region(REGION_NAME, null, null, null);
    }

    public TestBeacon findBeaconByMinor(int minor) {
        for (TestBeacon beacon : this) {
            if (beacon.getMinor() == minor) {
                return beacon;
            }
        }
        return null;
    }

    public TestBeacon getByName(String name) {
        for (TestBeacon beacon : this) {
            if (Objects.equals(beacon.getName(), name)) {
                return beacon;
            }
        }
        return null;
    }

    public boolean areAllDiscovered() {
        for (TestBeacon beacon : this) {
            if (!beacon.isDiscovered()) {
                return false;
            }
        }
        return true;
    }
}

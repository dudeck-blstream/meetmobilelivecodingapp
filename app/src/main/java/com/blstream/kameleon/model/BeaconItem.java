package com.blstream.kameleon.model;

import com.estimote.sdk.cloud.model.BeaconInfo;
import com.estimote.sdk.cloud.model.Color;

public class BeaconItem {

    public static final float MIN_ACCURACY = 0.1f;

    public static final float MAX_ACCURACY = 10;

    private boolean isDiscovered;

    private double accuracy = MAX_ACCURACY;

    private BeaconInfo beaconInfo;

    public BeaconItem(final BeaconInfo beaconInfo) {
        this.beaconInfo = beaconInfo;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(final boolean discovered) {
        isDiscovered = discovered;
    }

    public float getAccuracy() {
        return accuracy < MIN_ACCURACY ? MIN_ACCURACY : (float) accuracy;
    }

    public void setAccuracy(final double accuracy) {
        this.accuracy = (this.accuracy + accuracy) / 2;
    }

    public BeaconInfo getBeaconInfo() {
        return beaconInfo;
    }

    public Color getColor() {
        return beaconInfo.color;
    }
}

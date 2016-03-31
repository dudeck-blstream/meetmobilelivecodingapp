package com.blstream.kameleon.model;

import android.annotation.SuppressLint;

import com.estimote.sdk.cloud.model.BeaconInfo;


@SuppressLint("ParcelCreator")
public class BeaconItem {
    private boolean isDiscovered;
    private double accuracy;
    private BeaconInfo beaconInfo;


    public BeaconItem(BeaconInfo beaconInfo) {
        this.beaconInfo = beaconInfo;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(boolean discovered) {
        isDiscovered = discovered;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public BeaconInfo getBeaconInfo() {
        return beaconInfo;
    }
}

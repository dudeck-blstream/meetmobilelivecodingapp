package com.blstream.kameleon.model;

import android.annotation.SuppressLint;

import com.estimote.sdk.MacAddress;
import com.estimote.sdk.cloud.model.BeaconInfo;
import com.estimote.sdk.cloud.model.BeaconInfoSettings;
import com.estimote.sdk.cloud.model.Color;

import java.util.UUID;


@SuppressLint("ParcelCreator")
public class BeaconItem extends BeaconInfo {
    private boolean isDiscovered;

    public BeaconItem(UUID uuid, Integer major, Integer minor, MacAddress macAddress, String name, Color color, Double batteryLifeExpectancyInDays, BeaconInfoSettings settings) {
        super(uuid, major, minor, macAddress, name, color, batteryLifeExpectancyInDays, settings);
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered(boolean discovered) {
        isDiscovered = discovered;
    }
}

package com.blstream.kameleon.util;

import android.graphics.Color;

import com.blstream.kameleon.model.BeaconItem;

public class BeaconUtils {

    public static final double DISCOVER_MIN_VALUE = 0.5;
    public static final double DISCOVER_MAX_VALUE = 10;

    public static double obtainAlphaFactor(double beaconAccuracy) {
        return 1 - beaconAccuracy / DISCOVER_MAX_VALUE;
    }

    public static int adjustAlpha(BeaconItem beacon) {
        int color = beacon.getColor();
        double factor = obtainAlphaFactor(beacon.getAccuracy());
        int alpha = (int) Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}

package com.blstream.kameleon.util;

import com.blstream.kameleon.model.BeaconItem;
import com.estimote.sdk.cloud.model.Color;

import java.util.HashMap;
import java.util.Map;

public class BeaconUtils {

    public static final double DISCOVER_MIN_VALUE = 0.5;
    public static final double DISCOVER_MAX_VALUE = 10;

    private static final Map<Color, Integer> BEACON_COLORS = new HashMap<>();

    static {
        BEACON_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BEACON_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BEACON_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }


    public static double obtainAlphaFactor(double beaconAccuracy) {
        return 1 - beaconAccuracy / DISCOVER_MAX_VALUE;
    }

    public static int adjustAlpha(BeaconItem beacon) {
        int color = getColor(beacon);
        double factor = obtainAlphaFactor(beacon.getAccuracy());
        int alpha = (int) Math.round(android.graphics.Color.alpha(color) * factor);
        int red = android.graphics.Color.red(color);
        int green = android.graphics.Color.green(color);
        int blue = android.graphics.Color.blue(color);
        return android.graphics.Color.argb(alpha, red, green, blue);
    }

    private static int getColor(BeaconItem beaconItem){
        return BEACON_COLORS.get(beaconItem.getBeaconInfo().color);
    }
}

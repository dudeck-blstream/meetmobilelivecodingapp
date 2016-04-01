package com.blstream.kameleon.util;

import com.blstream.kameleon.model.BeaconItem;
import com.estimote.sdk.cloud.model.Color;

import java.util.HashMap;
import java.util.Map;

public class BeaconUtils {

    public static final double DISCOVER_MIN_VALUE = 0.01;

    public static final double DISCOVER_MAX_VALUE = 10;

    private static final Map<Color, Integer> BEACON_COLORS = new HashMap<>();

    static {
        BEACON_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BEACON_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BEACON_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    public static double obtainAlphaFactor(final double beaconAccuracy) {
        return 1 - (beaconAccuracy / DISCOVER_MAX_VALUE);
    }

    public static int adjustAlpha(final BeaconItem beacon) {
        final int color = getColor(beacon);
        final double factor = obtainAlphaFactor(beacon.getAccuracy());
        final int alpha = (int) Math.round(android.graphics.Color.alpha(color) * factor);
        final int red = android.graphics.Color.red(color);
        final int green = android.graphics.Color.green(color);
        final int blue = android.graphics.Color.blue(color);
        return android.graphics.Color.argb(alpha, red, green, blue);
    }

    public static int getColor(final BeaconItem beaconItem) {
        return BEACON_COLORS.get(beaconItem.getBeaconInfo().color);
    }
}

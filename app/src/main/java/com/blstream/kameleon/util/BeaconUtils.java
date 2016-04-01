package com.blstream.kameleon.util;

import com.blstream.kameleon.model.BeaconItem;
import com.estimote.sdk.cloud.model.Color;

import java.util.HashMap;
import java.util.Map;

public class BeaconUtils {

    public static final double DISCOVER_MIN_VALUE = 1.0;

    private static final Map<Color, Integer> BEACON_COLORS = new HashMap<>();

    static {
        BEACON_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BEACON_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BEACON_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    public static int getColor(final BeaconItem beaconItem) {
        return BEACON_COLORS.get(beaconItem.getBeaconInfo().color);
    }
}

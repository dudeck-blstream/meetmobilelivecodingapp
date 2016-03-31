package com.blstream.kameleon.model;


import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.estimote.sdk.cloud.model.Color;


public class CompetitionBeacons extends ArrayList<BeaconItem> {

    private static final Map<com.estimote.sdk.cloud.model.Color, Integer> BACKGROUND_COLORS = new HashMap<>();

    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final String REGION_NAME = "TestRegion";


    public static Region getRegion() {
        return new Region(REGION_NAME, null, null, null);
    }

    public BeaconItem findBeaconByMinor(int minor) {
        for (BeaconItem beacon : this) {
            if (beacon.minor == minor) {
                return beacon;
            }
        }
        return null;
    }

    public BeaconItem getByName(String name) {
        for (BeaconItem beacon : this) {
            if (Objects.equals(beacon.name, name)) {
                return beacon;
            }
        }
        return null;
    }

    public boolean areAllDiscovered() {
        for (BeaconItem beacon : this) {
            if (!beacon.isDiscovered()) {
                return false;
            }
        }
        return true;
    }
}

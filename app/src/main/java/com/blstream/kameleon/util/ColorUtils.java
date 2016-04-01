package com.blstream.kameleon.util;

import android.widget.ImageView;

public class ColorUtils {

    public static void setColorFilter(final ImageView view, final int color) {
//        view.getDrawable().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(color, PorterDuff.Mode.DST_OVER));
        view.setBackgroundColor(color);

    }
}

package com.blstream.kameleon;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class BeaconLine extends RelativeLayout {

    private int lineHeight = 0;

    public BeaconLine(final Context context) {
        super(context);
    }

    public BeaconLine(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public BeaconLine(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
        super.onLayout(changed, l, t, r, b);
        lineHeight = b - t;
    }

    public int getLineHeight(){
        return lineHeight;
    }
}

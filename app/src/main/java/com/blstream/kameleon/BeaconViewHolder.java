package com.blstream.kameleon;

import com.blstream.kameleon.model.BeaconItem;

import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeaconViewHolder {

    @Bind(R.id.background)
    View background;

    @Bind(R.id.logger)
    TextView logger;

    View rootView;

    private int screenHeight = -1;

    public BeaconViewHolder(ViewGroup view) {
        rootView = LayoutInflater.from(view.getContext()).inflate(R.layout.beacon_line, view, false);
        ButterKnife.bind(this, rootView);
        view.addView(rootView);
    }

    public void setAccuracy(float aprox) {
        //            float progress = (float) BeaconUtils.obtainAlphaFactor(beaconItem.getAccuracy());
        logger.setText(String.format("%.2f", aprox));

        float progress = aprox / BeaconItem.MAX_ACCURACY;
        if (progress > 1) {
            progress = 1;
        }
        setProgress(progress);
    }

    public int getHeight() {
        if (screenHeight != -1) {
            return screenHeight;
        }
        Display display = rootView.getDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        return screenHeight;
    }

    public void setColor(int color) {
        background.setBackgroundColor(color);
    }

    public void setProgress(float alpha) {
        background.animate()
                .setDuration(500)
                .alpha(1 - alpha)
                .translationY(alpha * getHeight())
                .start();
    }
}

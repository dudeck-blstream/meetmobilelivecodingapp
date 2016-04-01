package com.blstream.kameleon;

import com.blstream.kameleon.model.BeaconItem;

import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeaconViewHolder {

    @Bind(R.id.background)
    View background;

    @Bind(R.id.logger)
    TextView logger;

    @Bind(R.id.beacon_icon)
    ImageView icon;

    View rootView;

    private int screenHeight = -1;

    private boolean isDiscavered = false;

    public BeaconViewHolder(final ViewGroup view) {
        rootView = LayoutInflater.from(view.getContext()).inflate(R.layout.beacon_line, view, false);
        ButterKnife.bind(this, rootView);
        view.addView(rootView);
    }

    public void setAccuracy(final float aprox) {
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
        final Display display = rootView.getDisplay();
        final Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        return screenHeight;
    }

    public void setColor(final int color) {
        background.setBackgroundColor(color);
    }

    public void setDiscovered(final boolean discovered) {
        if (isDiscavered == discovered) {
            return;
        }

        if (discovered) {
            icon.setImageDrawable(ContextCompat.getDrawable(icon.getContext(), R.drawable.ic_action_done));
            icon.animate().scaleX(2).scaleY(2).setDuration(300).start();
        } else {
            icon.setImageDrawable(ContextCompat.getDrawable(icon.getContext(), R.drawable.beacon));
        }
    }

    public void setProgress(final float alpha) {
        background.animate()
                .setDuration(500)
                .alpha(1 - alpha)
                .translationY(alpha * getHeight())
                .start();
    }
}

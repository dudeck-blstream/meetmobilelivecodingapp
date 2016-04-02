package com.blstream.kameleon;

import com.blstream.kameleon.model.BeaconItem;

import android.support.v4.content.ContextCompat;
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

    BeaconLine rootView;

    private boolean isDiscovered = false;

    public BeaconViewHolder(final ViewGroup view) {
        rootView = (BeaconLine) LayoutInflater.from(view.getContext()).inflate(R.layout.beacon_line, view, false);
        ButterKnife.bind(this, rootView);
        background.setTranslationY(2000);
        view.addView(rootView);
    }

    public void setAccuracy(final float aprox) {
        logger.setText(String.format("%.2f", aprox));

        float progress = aprox / BeaconItem.MAX_ACCURACY;
        if (progress > 1) {
            progress = 1;
        }
        setProgress(progress);
    }

    public void setColor(final int color) {
        background.setBackgroundColor(color);
    }

    public void setDiscovered(final boolean discovered) {
        if (isDiscovered == discovered) {
            return;
        }
        isDiscovered = discovered;
        if (discovered) {
            icon.setImageDrawable(ContextCompat.getDrawable(icon.getContext(), R.drawable.ic_action_done));
        } else {
            icon.setImageDrawable(ContextCompat.getDrawable(icon.getContext(), R.drawable.beacon));
        }
    }

    public void setProgress(final float alpha) {
        background.animate()
                .setDuration(500)
                .alpha(1 - alpha)
                .translationY(alpha * rootView.getLineHeight())
                .start();
    }
}

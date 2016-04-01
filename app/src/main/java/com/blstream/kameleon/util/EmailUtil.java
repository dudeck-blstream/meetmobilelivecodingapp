package com.blstream.kameleon.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blstream.kameleon.R;

public class EmailUtil {
    public static void sendEmail(final Context context) {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", context.getString(R.string.bls_email_address), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MeetMobile 5/4/2016 contest");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Am I the winner?");

        context.startActivity(Intent.createChooser(emailIntent, "Choose your email app"));
    }
}

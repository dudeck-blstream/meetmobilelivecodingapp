package com.blstream.kameleon;

import com.estimote.sdk.EstimoteSDK;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //  App ID & App Token can be taken from App section of Estimote Cloud.
        EstimoteSDK.initialize(getApplicationContext(), "blstream-sp--z-o-o--s-meet-81f", "0cf0ca5624ff00f5de4fdf6f39841bdb");

        // Optional, debug logging.
        EstimoteSDK.enableDebugLogging(true);
    }
}

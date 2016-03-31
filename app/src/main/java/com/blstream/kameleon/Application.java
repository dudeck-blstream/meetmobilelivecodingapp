package com.blstream.kameleon;

import com.estimote.sdk.EstimoteSDK;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //  App ID & App Token can be taken from App section of Estimote Cloud.
        EstimoteSDK.initialize(getApplicationContext(), "cameleon-5vf", "b706e95f843b8b10b8f19a82f69c48e2");

        // Optional, debug logging.
        EstimoteSDK.enableDebugLogging(true);
    }
}

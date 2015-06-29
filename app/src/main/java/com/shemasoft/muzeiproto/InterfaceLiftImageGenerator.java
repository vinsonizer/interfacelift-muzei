package com.shemasoft.muzeiproto;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.MuzeiArtSource;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource;

/**
 * Created by jv on 6/24/2015.
 */
public class InterfaceLiftImageGenerator extends RemoteMuzeiArtSource {


    private static final String TAG = "InterfaceLiftMuzei";

    public InterfaceLiftImageGenerator() {
        super(TAG);
        Log.d(TAG, "Initializing Local Image Generator");
    }

    @Override
    protected void onEnabled() {
        super.onEnabled();
        unscheduleUpdate();
        scheduleUpdate(getUpdateInterval());
    }

    @Override
    protected void onTryUpdate(int reason) throws RetryException {
        Log.d(TAG, "in onTryUpdate");
        try {
            String link = InterfaceLiftDownloadHandler.getLink();
            setMuzeiImage(link);
        } catch (Exception e) {
            Log.e(TAG, "Exception getting data: ", e);
            throw new RetryException(e);
        }
    }

    private void setMuzeiImage(String link) {
        Log.d(TAG, "setting image to " + link);
        publishArtwork(new Artwork.Builder()
                .title("InterfaceLift Image")
                .imageUri(Uri.parse(link))
                .viewIntent(new Intent(Intent.ACTION_VIEW, Uri.parse(link)))
                .build());

        scheduleUpdate(getUpdateInterval());
    }

    private long getUpdateInterval() {
        Integer refreshTime = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString("pref_refresh_period", "60"));
        Log.d(TAG, "Refresh time set to " + refreshTime + " minutes");
        return System.currentTimeMillis() + (refreshTime * 60 * 1000);
    }

}

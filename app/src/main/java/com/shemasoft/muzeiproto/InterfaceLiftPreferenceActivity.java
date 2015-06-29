package com.shemasoft.muzeiproto;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by jv on 6/25/2015.
 */
public class InterfaceLiftPreferenceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new InterfaceLiftPreferenceFragment())
                .commit();
    }
}

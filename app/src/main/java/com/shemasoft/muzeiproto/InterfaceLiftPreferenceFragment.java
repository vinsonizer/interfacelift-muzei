package com.shemasoft.muzeiproto;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by jv on 6/25/2015.
 */
public class InterfaceLiftPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}

package com.example.androidsimplesettingsactivity.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.example.androidsimplesettingsactivity.R;
import com.example.androidsimplesettingsactivity.datastorage.SaveDataOnPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {


    private SaveDataOnPreference saveDataOnPreference;

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {

        setPreferencesFromResource(R.xml.settings_preference, rootKey);

        Preference switchPreference = findPreference(getResources().getString(R.string.switch_auto_record));

        // Initialize Save Data in Local
        saveDataOnPreference = new SaveDataOnPreference(getContext());

        // Update Auto Record Status
        updateAutoRecordStatus();

        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                // Update Auto Record Status
                updateAutoRecordStatus();
                return true;
            }
        });
    }

    /**
     * Update Auto Record Status Method
     */
    private void updateAutoRecordStatus() {

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean autoRecord = sharedPreferences.getBoolean(getResources().getString(R.string.switch_auto_record), false);

        // Set the updated auto record status in the Save data class
        saveDataOnPreference.setAutoRecord(autoRecord);

        Log.d("RRR", "updateAutoRecordStatus: " + saveDataOnPreference.getAutoRecord());
    }

}

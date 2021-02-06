package com.example.androidsimplesettingsactivity.datastorage;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveDataOnPreference {

    private static final String APP_PREFS_NAME = "SavedData";

    // preference instance
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    // all keys name
    public static final String AUTO_RECORD = "switch_preference_record";

    /**
     * initialize instance
     * @param context
     */
    public SaveDataOnPreference(Context context)
    {
        this.appSharedPrefs = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    /**
     * Set Auto Record Status
     * @param value -> Boolean
     */
    public void setAutoRecord(boolean value){
        prefsEditor.putBoolean( AUTO_RECORD, value);
        prefsEditor.commit();
    }

    /**
     * Get Auto Record Status
     * @return
     */
    public boolean getAutoRecord() {
        return appSharedPrefs.getBoolean(AUTO_RECORD, false);
    }
}

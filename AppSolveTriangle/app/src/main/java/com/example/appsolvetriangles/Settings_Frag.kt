package com.example.appsolvetriangles

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class Settings_Frag : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
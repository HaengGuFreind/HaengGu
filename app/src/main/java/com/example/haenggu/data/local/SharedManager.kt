package com.example.haenggu.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.haenggu.data.local.PreferenceHelper.set
import com.example.haenggu.data.local.PreferenceHelper.get

class SharedManager(context: Context) {

    private val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(context)

    fun saveHToken(htoken: String) {
        prefs["htoken"] = htoken
    }

    fun getHToken(): String {
        var htoken = prefs["htoken", ""]
        return htoken
    }
}
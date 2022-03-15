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

    fun saveEmail(email: String) {
        prefs["email"] = email
    }

    fun getEmail(): String {
        var email = prefs["email", ""]
        return email
    }

    fun saveBirth(birth: String) {
        prefs["birth"] = birth
    }

    fun getBirth(): String {
        var birth = prefs["birth", ""]
        return birth
    }

    fun saveGender(gender: String) {
        prefs["gender"] = gender
    }

    fun getGender(): String {
        var gender = prefs["gender", ""]
        return gender
    }
}
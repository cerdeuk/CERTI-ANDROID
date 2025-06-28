package org.sopt.certi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class CertiApp : Application(){
    override fun onCreate() {
        super.onCreate()
        setDarkMode()
    }

    private fun setDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
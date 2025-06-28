package org.sopt.certi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CERTIApp : Application(){
    override fun onCreate() {
        super.onCreate()
        setDarkMode()
    }

    private fun setDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
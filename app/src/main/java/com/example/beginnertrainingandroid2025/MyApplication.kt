package com.example.beginnertrainingandroid2025

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        LocalDataSourceFactory.initialize(this)
    }
}
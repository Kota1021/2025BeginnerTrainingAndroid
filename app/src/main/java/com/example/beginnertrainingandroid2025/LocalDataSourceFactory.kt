package com.example.beginnertrainingandroid2025

import android.app.Application
import androidx.room.Room

object LocalDataSourceFactory {
    private lateinit var appDatabase: AppDatabase

    fun initialize(app: Application) {
        appDatabase =  Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_database",
        ).build()
    }

    fun createRepoLocalDataSource() = RepoLocalDataSource(dao = appDatabase.repoDao())
}
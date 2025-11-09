package com.example.beginnertrainingandroid2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.beginnertrainingandroid2025.ui.theme.BeginnerTrainingAndroid2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeginnerTrainingAndroid2025Theme {

                BeginnerTrainingApp()
            }
        }
    }
}


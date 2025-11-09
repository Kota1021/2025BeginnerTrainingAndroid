package com.example.beginnertrainingandroid2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.beginnertrainingandroid2025.ui.theme.BeginnerTrainingAndroid2025Theme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeginnerTrainingAndroid2025Theme {
                HomeScreen(
                    viewModel = viewModel(factory = HomeViewModel.Factory)
                )
            }
        }
    }
}


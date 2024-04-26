package com.app.lydiatest.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.lydiatest.navigation.NavigationConfig
import com.app.lydiatest.presenter.ui.theme.LydiaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LydiaTestTheme {
                NavigationConfig()
            }
        }
    }
}

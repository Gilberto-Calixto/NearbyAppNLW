 package com.filnm.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.filnm.nearby.ui.screen.HomeScreen.HomeScreen
import com.filnm.nearby.ui.theme.NearbyTheme

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                HomeScreen()
            }
        }
    }
}


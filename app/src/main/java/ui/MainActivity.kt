package com.example.cookmate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cookmate.ui.navigation.CookMateNavGraph
import com.example.cookmate.ui.theme.CookMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Khởi tạo biến điều khiển chuyển màn hình
                    val navController = rememberNavController()

                    // Gắn vào hệ thống NavGraph
                    CookMateNavGraph(navController = navController)
                }
            }
        }
    }
}

package com.example.cookmate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cookmate.ui.auth.HomeScreen
import com.example.cookmate.ui.auth.LoginScreen
import com.example.cookmate.ui.auth.HomeScreen
import com.example.cookmate.ui.onboarding.OnboardingScreen
import com.example.cookmate.ui.splash.SplashScreen

@Composable
fun CookMateNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route // Mở app là vào Splash đầu tiên
    ) {
        // 1. Màn hình Splash
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        // 2. Màn hình Onboarding
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }

        // 3. Màn hình Login
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        // Thêm dòng này vào bên trong NavHost { ... }
        composable("home_screen") {
            HomeScreen()
        }
    }
}
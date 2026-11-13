

package com.example.cookmate.ui.splash
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cookmate.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookmate.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Tự động chuyển sang Onboarding sau 2 giây
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(Screen.Onboarding.route) {
            // Xóa Splash khỏi ngăn xếp để khi ấn nút Back không quay lại đây
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    // Giao diện (Tạm thời dùng màu xanh lục nhạt gần giống Figma)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF69FA97)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // TODO: Thay bằng icon cái nồi/chậu cây thực tế của bạn
            Image(
                painter = painterResource(id = R.drawable.logoapp), // Đổi "logo_cookmate" thành đúng tên file ảnh của bạn
                contentDescription = "Logo CookMate",
                modifier = Modifier.size(120.dp) // Tùy chỉnh kích thước to/nhỏ ở đây bằng dp
            )
            Text(
                text = "COOKMATE",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Text(
                text = "Hãy biến nguyên liệu thành món ăn ngay nào",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
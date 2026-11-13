

package com.example.cookmate.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookmate.R // Nhớ sửa package
import com.example.cookmate.ui.navigation.Screen
import kotlinx.coroutines.launch

// Dữ liệu cho các trang Onboarding
data class OnboardingPageInfo(
    val imageRes: Int,
    val title: String,
    val description: String,
    val buttonText: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = listOf(
        OnboardingPageInfo(
            imageRes = R.drawable.tulanh,
            title = "Hôm nay bếp bạn có gì ?",
            description = "Chọn những nguyên liệu bạn đang có để khám phá các món ăn có thể chế biến",
            buttonText = "Tiếp tục"
        ),
        OnboardingPageInfo(
            imageRes = R.drawable.chebien,
            title = "Bắt tay vào nấu nào !",
            description = "Tìm kiếm công thức yêu thích, lưu lại món ngon và biến mỗi bữa ăn thành niềm vui",
            buttonText = "Bắt đầu ngay"
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header: Logo và nút Bỏ qua
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logoapp), // TODO: Ảnh logo nhỏ góc trái
                    contentDescription = "Logo",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cook mate", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
                Text("Bỏ qua", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Pager cho hình ảnh và nội dung
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { position ->
            OnboardingPage(page = pages[position])
        }

        // Indicator (Dấu chấm) và Nút bấm dưới cùng
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Chỉ báo trang
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                repeat(pages.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color(0xFF4CAF50) else Color.LightGray
                    val width = if (pagerState.currentPage == iteration) 24.dp else 8.dp
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .height(8.dp)
                            .width(width)
                    )
                }
            }

            // Nút điều hướng
            Button(
                onClick = {
                    if (pagerState.currentPage == pages.size - 1) {
                        // Trang cuối thì qua Login
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    } else {
                        // Chưa đến trang cuối thì lướt sang trang tiếp theo
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = pages[pagerState.currentPage].buttonText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun OnboardingPage(page: OnboardingPageInfo) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hình ảnh minh họa
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Khối nền xanh chứa Text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFA5E6B5))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = page.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = page.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
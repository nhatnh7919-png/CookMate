package com.example.cookmate.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookmate.R // Đảm bảo package R này khớp với dự án của bạn
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    // Mã màu nền xanh sáng từ thiết kế
    val bgColor = Color(0xFF6EFA9B)
    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = androidx.compose.runtime.rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Hình ảnh nồi lẩu
        Image(
            painter = painterResource(id = R.drawable.logoapp),
            contentDescription = "Hotpot",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Text Chào mừng
        Text(
            text = "Chào mừng bạn đến với",
            fontSize = 24.sp,
            color = Color.Black
        )
        Text(
            text = "COOKMATE",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD32F2F) // Màu đỏ đậm
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Mô tả
        Text(
            text = "Đăng nhập để lưu công việc và đồng bộ trên mọi thiết bị của bạn",
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Đẩy phần dưới cùng xuống đáy

        // Nút Google đã được tích hợp logic xử lý đăng nhập
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val credentialManager = androidx.credentials.CredentialManager.create(context)

                        val googleIdOption = com.google.android.libraries.identity.googleid.GetGoogleIdOption.Builder()
                            .setFilterByAuthorizedAccounts(false)
                            .setServerClientId("346384184399-gfutvlimk6e86qcruiudf1kamu9k70qq.apps.googleusercontent.com")
                            .setAutoSelectEnabled(true)
                            .build()

                        val request = androidx.credentials.GetCredentialRequest.Builder()
                            .addCredentialOption(googleIdOption)
                            .build()

                        // Mở popup chọn tài khoản Google
                        val result = credentialManager.getCredential(context, request)
                        val credential = result.credential

                        if (credential is androidx.credentials.CustomCredential &&
                            credential.type == com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

                            val googleIdTokenCredential = com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.createFrom(credential.data)
                            val idToken = googleIdTokenCredential.idToken
                            android.util.Log.d("GoogleAuth", "Đăng nhập thành công, Token: $idToken")

                            // Thêm đoạn này để chuyển sang màn hình tạm
                            navController.navigate("home_screen") {
                                // Xóa LoginScreen khỏi lịch sử để khi bấm nút Back trên điện thoại không bị quay lại màn hình đăng nhập
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }

                            // Chuyển sang màn hình tạm và xóa LoginScreen khỏi ngăn xếp Navigation
                            navController.navigate("temp_screen") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    } catch (e: Exception) {
                        android.util.Log.e("GoogleAuth", "Lỗi đăng nhập", e)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3E5F5)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Tiếp tục với Google",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Đường gạch ngang "hoặc"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
            Text(
                text = " hoặc ",
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Dùng thử không cần tài khoản
        TextButton(onClick = { /* TODO: Navigate to Main Home */ }) {
            Text(
                text = "Dùng thử không cần tài khoản",
                color = Color(0xFF5C6BC0), // Màu xanh tím
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
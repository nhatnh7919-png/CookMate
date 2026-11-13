

package com.example.cookmate.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class TokenManager(context: Context) {
    // 1. Tạo MasterKey bằng phần cứng của thiết bị
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    // 2. Khởi tạo file SharedPreferences đã được mã hóa
    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "cookmate_secure_prefs", // Tên file
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // 3. Hàm lưu JWT
    fun saveJwtToken(token: String) {
        sharedPreferences.edit().putString("JWT_TOKEN", token).apply()
    }

    // 4. Hàm lấy JWT ra để dùng sau này (ví dụ: gắn vào Header của Retrofit)
    fun getJwtToken(): String? {
        return sharedPreferences.getString("JWT_TOKEN", null)
    }
}
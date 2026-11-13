package com.example.cookmate.domain.model

// Đóng gói các thuộc tính của một món ăn
data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val timeToCookMinutes: Int
) {
    // OOP: Phương thức xử lý dữ liệu nằm ngay trong đối tượng
    fun getCookingTimeLabel(): String {
        return "$timeToCookMinutes phút"
    }
}
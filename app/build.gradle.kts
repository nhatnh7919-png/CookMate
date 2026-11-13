plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services") // Thêm dòng này

}

android {
    namespace = "com.example.cookmate"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.cookmate"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // . Credential Manager API (Chuẩn mới thay thế Google Sign-In)
    implementation("androidx.credentials:credentials:1.3.0-alpha01")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0-alpha01")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // . Security Crypto (Mã hóa SharedPreferences)
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    //  ViewModel Compose (Để quản lý trạng thái UI)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    // 1. Navigation Compose (Chuyển màn hình)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // 2. Hilt (Dependency Injection - Quản lý khởi tạo code)
    implementation("com.google.dagger:hilt-android:2.60.1")
    implementation(libs.androidx.compose.foundation.layout)
    ksp("com.google.dagger:hilt-compiler:2.60.1")

    // 3. Retrofit & OkHttp (Gọi API Backend)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // 4. Coil (Hiển thị ảnh từ Internet cho Compose)
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
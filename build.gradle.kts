// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Hilt
    id("com.google.dagger.hilt.android") version "2.60.1" apply false

    // KSP thay thế KAPT
    id("com.google.devtools.ksp") version "2.3.10" apply false
    id("com.google.gms.google-services") version "4.5.0" apply false

}
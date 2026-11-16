
plugins {
    id("com.android.application") version "8.7.3"
    id("org.jetbrains.kotlin.android") version "2.0.21"
}

android {
    namespace = "com.crystalkey.keyboard"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.crystalkey.keyboard"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

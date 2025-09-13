plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "hu.garzilla.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "hu.garzilla.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Java/Kotlin egységes beállítás
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")

    // Osmdroid
    implementation("org.osmdroid:osmdroid-android:6.1.20")
    implementation("org.osmdroid:osmdroid-android:6.1.20")

    // Mapsforge
    implementation("org.mapsforge:mapsforge-map-android:0.20.0")
    implementation("org.mapsforge:mapsforge-map:0.20.0")
    implementation("org.mapsforge:mapsforge-themes:0.20.0")

    // Bridge osmdroid <-> mapsforge
    implementation("org.osmdroid:osmdroid-mapsforge:6.1.10")
}

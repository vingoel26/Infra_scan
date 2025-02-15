plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") version "4.4.2" apply true
}

android {
    namespace = "com.example.infrascan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.infrascan"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}


buildscript {
    dependencies {
        classpath ("com.google.gms:google-services:4.4.0")  // ✅ Add this
    }
}

dependencies {

    implementation (libs.firebase.firestore)
    implementation(libs.play.services.auth)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation (libs.firebase.storage.v2021)

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation (libs.firebase.auth)
    implementation (libs.firebase.storage)
    implementation (libs.firebase.database)
    implementation (libs.firebase.ui.storage)
    implementation (libs.okhttp3.okhttp)
    implementation("io.appwrite:sdk-for-android:6.1.0")
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.squareup.logging.interceptor)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}


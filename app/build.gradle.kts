plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.myweatherappchallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myweatherappchallenge"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "${property("API_KEY")}")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    packaging {
        dex {
            useLegacyPackaging = false
        }
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose)
    implementation(libs.compose.preview)
    implementation(libs.compose.rxjava)
    implementation(libs.compose.activity)
    implementation(libs.compose.livedata)
    implementation(libs.compose.lifecycle)
    implementation(libs.hilt.android)
    implementation(libs.material.compose)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.data.store)
    implementation(libs.okhttp)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.coil)
    implementation(libs.location.services)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.play.services)
    implementation(libs.transport.runtime)
    implementation(libs.permissions)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.hilt.android.testing)
    debugImplementation(libs.compose.debug)
}
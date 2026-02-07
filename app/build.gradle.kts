import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "org.sopt.certi"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.sopt.certi"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", properties["certi.base.url"].toString())

        val kakaoNativeAppKey = properties["kakao.native.app.key"].toString()
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"$kakaoNativeAppKey\"")
        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = kakaoNativeAppKey

        val googleClientID = properties["google.web.client.id"].toString()
        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"$googleClientID\"")
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file("$rootDir/keystore/debug.keystore")
            storePassword = "android"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.firebase.crashlytics.buildtools)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.kotlinx.immutable)

    // Network
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization.converter)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coil
    implementation(libs.coil)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    ksp(libs.hilt.compiler)

    // Timber
    implementation(libs.timber)

    // Kakao
    implementation(libs.kakao.user)

    // lottie
    implementation(libs.lottie.compose)

    // calendar
    implementation(libs.kizitonwose.calendar)

    // Google
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}

ktlint {
    android = true
    debug = true
    coloredOutput = true
    verbose = true
    outputToConsole = true
}

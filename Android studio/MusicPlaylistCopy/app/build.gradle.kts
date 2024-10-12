plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.musicplaylistcopy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.musicplaylistcopy"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders += mapOf("redirectHostName" to "com.example",
                                        "redirectSchemeName" to "myapp")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "34.0.0"
}

dependencies {
    implementation (files("../app-remote-lib/spotify-app-remote-release-0.8.0.aar"))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
//    implementation(project(":auth-lib"))
//    implementation(project(":app-remote-lib"))
    implementation(libs.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // your app dependencies
//    implementation ("androidx.browser:browser:1.8.0")
}
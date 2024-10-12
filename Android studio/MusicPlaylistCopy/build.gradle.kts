// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2") // Replace with your desired version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0") // Replace with your desired version
    }
}
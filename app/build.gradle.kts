plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.videosapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.videosapp"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.android.exoplayer:exoplayer:2.14.2")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("com.google.firebase:firebase-auth:20.0.3")
    implementation("com.google.firebase:firebase-firestore:23.0.3")
    implementation("com.google.firebase:firebase-storage:19.2.2")

}
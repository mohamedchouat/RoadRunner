plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.chtmed.roadrunner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chtmed.roadrunner"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
         kotlinCompilerExtensionVersion = "1.5.3"
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
     implementation(platform(libs.compose.bom))

     implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.tooling.preview)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

     implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.core)


    implementation(libs.rxjava3)
    implementation(libs.rxkotlin3)

     implementation(libs.work.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)

     implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

     implementation(libs.material)

     debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.ui.test.manifest)


    androidTestImplementation(libs.compose.ui.test.junit4)
}

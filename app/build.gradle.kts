plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.berkaykurtoglu.worktracker"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.berkaykurtoglu.worktracker"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}



dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.compose.material:material")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")



    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.navigation:navigation-compose:2.6.0-rc01")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.17.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    //Dagger - Hilt
    /*implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.hilt:hilt-work:1.0.0")
    implementation ("androidx.work:work-runtime-ktx:2.8.1")*/


    // Coil
    implementation ("io.coil-kt:coil-compose:2.4.0")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")


    implementation("androidx.navigation:navigation-compose:2.6.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    //Time picker
    implementation ("com.maxkeppeler.sheets-compose-dialogs:core:1.2.0")
    implementation ("com.maxkeppeler.sheets-compose-dialogs:calendar:1.2.0")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    //Lottie
    implementation ("com.airbnb.android:lottie-compose:6.1.0")

    //Icons



}
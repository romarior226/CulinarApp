plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.culinarapp"
    compileSdk = 36 // Виправив на стандартний синтаксис

    defaultConfig {
        applicationId = "com.example.culinarapp"
        minSdk = 30
        targetSdk = 36 // Зазвичай співпадає з compileSdk
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // 1. Стандартні AndroidX бібліотеки (з твого каталогу libs)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.compose.material:material-icons-extended:1.6.0")

    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1") // Annotation processor
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("androidx.activity:activity-ktx:1.8.0")
// For Activities
    implementation("androidx.fragment:fragment-ktx:1.6.0")
// For Fragments
    implementation("androidx.activity:activity-compose:1.8.2") // або новіша
    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // 2. Compose BOM (Сам керує версіями всіх Compose-бібліотек!)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Якщо ти все ж хочеш використовувати LiveData з Compose (BOM підтягне версію)
    implementation("androidx.compose.runtime:runtime-livedata")

    // 3. Навігація та ViewModel (найновіші стабільні версії)
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    // 4. Додаткові інструменти
    implementation("com.google.code.gson:gson:2.10") // Залишаємо, якщо потрібен для іншого
    implementation("io.coil-kt:coil-compose:2.6.0")  // Для завантаження картинок

    // 5. Тестування та Debug
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
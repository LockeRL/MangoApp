plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.locker.mangotestapp"
    compileSdk = Android.DefaultConfig.compileSdk

    defaultConfig {
        applicationId = Android.DefaultConfig.applicationId
        minSdk = Android.DefaultConfig.minSdk
        targetSdk = Android.DefaultConfig.targetSdk
        versionCode = Android.DefaultConfig.versionCode
        versionName = Android.DefaultConfig.versionName

        testInstrumentationRunner = Android.DefaultConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = Android.DefaultConfig.VectorDrawables.useSupportLibrary
        }

        room {
            schemaDirectory("$projectDir/schemas")
        }

        buildConfigField("String", "BASE_URL", "\"https://plannerok.ru/api/v1/users\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = Android.BuildTypes.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Android.CompileOptions.sourceCompatibility
        targetCompatibility = Android.CompileOptions.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = Android.KotlinOptions.jvmTarget
    }

    buildFeatures {
        compose = Android.BuildFeatures.compose
        buildConfig = Android.BuildFeatures.buildConfig
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Android.ComposeOptions.kotlinCompilerExtensionVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Permissions
    implementation(libs.google.accompanist)

    // Phone
    implementation(libs.ccp)
    implementation(libs.libphonenumber)

    // Coroutines
    implementation(libs.coroutines.core)

    // Ktor
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)

    // Navigation
    implementation(libs.ui.compose.navigation)

    // DB Room
    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Koin DI
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

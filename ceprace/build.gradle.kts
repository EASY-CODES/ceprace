plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

group = "com.github.EASY-CODES"

android {
    namespace = "com.engedu.ceprace"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    testImplementation("junit:junit:4.13.2")

    // Retrofit para chamadas de API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Para coroutines com Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // testes
    androidTestImplementation("io.mockk:mockk-android:1.12.0")
    androidTestImplementation("io.mockk:mockk-common:1.12.0")

    //injeção de dependencia
    implementation("io.insert-koin:koin-android:3.5.3")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.EASY-CODES"
            artifactId = "ceprace"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
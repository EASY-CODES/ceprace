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
            isMinifyEnabled = true
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

    packaging {
        resources {
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {

    implementation("androidx.test.ext:junit-ktx:1.1.5")

    //debugImplementation("com.squareup.leakcanary:leakcanary-android:3.0-alpha-1")

    // Retrofit para chamadas de API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Para coroutines com Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // testes
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")


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
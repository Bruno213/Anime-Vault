import java.io.FileInputStream
import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.apollo.kotlin)

  alias(libs.plugins.kotlin.ksp)
}

android {
  val myProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "env.properties")))
  }

  namespace = "com.example.animevault"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.example.animevault"
    minSdk = 29
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

      buildConfigField("String", "ANI_LIST_API", myProperties.getProperty("ANI_LIST_API"))
    }

    debug {
      applicationIdSuffix = ".debug"

      buildConfigField("String", "ANI_LIST_API", myProperties.getProperty("ANI_LIST_API"))
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
    buildConfig = true
    compose = true
  }

  apollo {
    service("service") {
      packageName.set("com.anilist.graphQL")
      introspection {
        endpointUrl.set(myProperties.getProperty("ANI_LIST_API"))
        schemaFile.set(file("src/main/graphql/schema.graphqls")) //replace with the schema file location
      }
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

  implementation(libs.androidx.animation)
  implementation(libs.lottie.compose)
  implementation(libs.coil.compose)
  implementation(libs.coil.network.okhttp)

  implementation (libs.androidx.navigation.compose)

  implementation(libs.apollo.runtime)
  implementation(libs.logging.interceptor)
  // Room
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.runtime)
  annotationProcessor(libs.androidx.room.compiler)
  ksp(libs.androidx.room.compiler)
  androidTestImplementation(libs.androidx.room.testing)
  implementation(libs.androidx.datastore.preferences)

  implementation(libs.koin.androidx.compose)

// Koin for Tests
  testImplementation(libs.koin.test.junit4)

  implementation(libs.timber)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.

allprojects {
  repositories {
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
  }
}

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false

  alias(libs.plugins.kotlin.ksp) apply false
}

tasks {
  register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
  }
}
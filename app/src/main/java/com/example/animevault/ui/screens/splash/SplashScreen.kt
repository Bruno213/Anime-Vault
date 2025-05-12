package com.example.animevault.ui.screens.splash

import androidx.compose.runtime.Composable
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.theme.AppPreviews

@Composable
fun SplashScreen() {
  Content()
}

@Composable
private fun Content() {

}

@AppPreviews
@Composable
fun SplashScreenPreview() {
  AppThemePreview {
    SplashScreen()
  }
}
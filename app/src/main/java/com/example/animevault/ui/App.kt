package com.example.animevault.ui

import android.os.Build
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.animevault.ui.navigation.AppNavigation
import com.example.animevault.ui.theme.AnimeVaultTheme

@Composable
fun AppStart() {
  AnimeVaultTheme {
    Surface(modifier = Modifier.applySafeDrawing()) {
      AppNavigation()
    }
  }
}

@Composable
fun AppThemePreview(content: @Composable ()-> Unit) {
  AnimeVaultTheme {
    Surface(modifier = Modifier.applySafeDrawing(), content = content)
  }
}

@Composable
fun Modifier.applySafeDrawing(): Modifier {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) return this
  return this.windowInsetsPadding(WindowInsets.safeDrawing)
}
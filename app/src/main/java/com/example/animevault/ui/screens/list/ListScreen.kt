package com.example.animevault.ui.screens.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.theme.AppPreviews
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(viewModel: ListViewModel = koinViewModel()) {
  Content()
}

@Composable
private fun Content() {
  Text("Hello World")
}

@AppPreviews
@Composable
fun ListScreenPreview() {
  AppThemePreview {
    ListScreen()
  }
}
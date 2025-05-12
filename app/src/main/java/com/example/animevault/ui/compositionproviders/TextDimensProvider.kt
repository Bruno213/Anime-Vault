package com.example.animevault.ui.compositionproviders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.animevault.ui.theme.TextDimens


private val LocalTextDimens = staticCompositionLocalOf {
  TextDimens.compactDimens
}

@Composable
fun ProvideTextDimens(
  content: @Composable () -> Unit
) {
  val dimensionSet = remember { TextDimens.compactDimens }
  CompositionLocalProvider(LocalTextDimens provides dimensionSet, content = content)
}


object AppDimens {
  val fontSizes: TextDimens
    @Composable
    get() = LocalTextDimens.current
}
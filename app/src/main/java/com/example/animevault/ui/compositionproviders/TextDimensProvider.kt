package com.offerwise.PanelApps.ui.compose.compositionproviders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.offerwise.PanelApps.ui.compose.theme.TextDimens
import com.offerwise.PanelApps.utils.WindowType


private val LocalTextDimens = staticCompositionLocalOf {
  TextDimens.compactDimens
}

@Composable
fun ProvideTextDimens(
  content: @Composable () -> Unit
) {

  val windowInfo = LocalWindowInfo.current

  val dimensionSet = remember {
    if(windowInfo.screenWidthInfo == WindowType.Compact) TextDimens.compactDimens else TextDimens.mediumDimens
  }
  CompositionLocalProvider(LocalTextDimens provides dimensionSet, content = content)
}


object AppDimens {
  val fontSizes: TextDimens
    @Composable
    get() = LocalTextDimens.current
}
package com.example.animevault.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animevault.R
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.compositionproviders.AppDimens
import com.example.animevault.ui.navigation.Routes
import com.example.animevault.ui.theme.AppPreviews
import com.example.animevault.ui.theme.Blue1000
import com.example.animevault.ui.theme.LightBlue200
import com.offerwise.PanelApps.ui.compose.compositionproviders.LocalNavigator
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
  val navigator = LocalNavigator.current

  LaunchedEffect("start") {
    delay(3000)
    navigator.navigateTo(Routes.List.route)
  }

  Content()
}

@Composable
private fun Content() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Blue1000),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ){
    Image(
      modifier = Modifier.size(100.dp),
      painter = painterResource(R.drawable.logo),
      contentDescription = null
    )

    Text(
      text = stringResource(R.string.app_name),
      color = LightBlue200,
      fontSize = AppDimens.fontSizes.XXXL,
      fontWeight = FontWeight.SemiBold
    )
  }
}

@AppPreviews
@Composable
fun SplashScreenPreview() {
  AppThemePreview {
    SplashScreen()
  }
}
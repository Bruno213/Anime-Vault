package com.example.animevault.ui.navigation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animevault.ui.screens.filter.FilterScreen
import com.example.animevault.ui.screens.list.AnimeScreen
import com.example.animevault.ui.screens.splash.SplashScreen
import com.offerwise.PanelApps.ui.compose.compositionproviders.ProvideNavigator


@Composable
fun  AppNavigation() {
  val navController = rememberNavController()

  ProvideNavigator(navController = navController) {
    NavHost(
      route = NavigationGraphs.MAIN.name,
      navController = navController,
      startDestination = Routes.Splash.route
    ) {
      composable(
        Routes.Splash.route,

        enterTransition = {
          fadeIn(animationSpec = tween(easing = LinearEasing, durationMillis = 400))
        },

        exitTransition = {
          fadeOut(animationSpec = tween(easing = LinearEasing, durationMillis = 400))
        },
      ) {
        SplashScreen()
      }

      composable(
        Routes.List.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
      ) {
        AnimeScreen()
      }

      composable(
        Routes.Filter.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
      ) {
        FilterScreen()
      }
    }
  }
}
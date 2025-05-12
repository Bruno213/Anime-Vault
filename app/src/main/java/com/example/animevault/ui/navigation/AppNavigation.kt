package com.example.animevault.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animevault.ui.screens.details.DetailsScreen
import com.example.animevault.ui.screens.filter.FilterScreen
import com.example.animevault.ui.screens.list.ListScreen
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
        enterTransition = {
          slideIn(
            animationSpec = tween(easing = LinearEasing, durationMillis = 400),
            initialOffset = { return@slideIn IntOffset(x = it.width, y = 0) }
          )
        },

        exitTransition = {
          ExitTransition.KeepUntilTransitionsFinished + ExitTransition.None
        },

        popEnterTransition = {
          EnterTransition.None
        },

        popExitTransition = {
          slideOut(
            animationSpec = tween(easing = LinearEasing, durationMillis = 400),
            targetOffset = { return@slideOut IntOffset(x = it.width, y = -0) }
          )
        }
      ) {
        ListScreen()
      }

      composable(
        Routes.Details.route,
        enterTransition = {
          slideIn(
            animationSpec = tween(easing = LinearEasing, durationMillis = 400),
            initialOffset = { return@slideIn IntOffset(x = it.width, y = 0) }
          )
        },

        exitTransition = {
          ExitTransition.KeepUntilTransitionsFinished + ExitTransition.None
        },

        popEnterTransition = {
          EnterTransition.None
        },

        popExitTransition = {
          slideOut(
            animationSpec = tween(easing = LinearEasing, durationMillis = 400),
            targetOffset = { return@slideOut IntOffset(x = it.width, y = -0) }
          )
        }
      ) {
        DetailsScreen()
      }

      composable(
        Routes.Filter.route,
        enterTransition = {
          slideIn(
            animationSpec = tween(easing = LinearEasing, durationMillis = 400),
            initialOffset = { return@slideIn IntOffset(x = it.width, y = 0) }
          )
        },

        exitTransition = {
          ExitTransition.KeepUntilTransitionsFinished + ExitTransition.None
        },

        popEnterTransition = {
          EnterTransition.None
        },

        popExitTransition = {
          slideOut(
            animationSpec = tween(easing = LinearEasing, durationMillis = 400),
            targetOffset = { return@slideOut IntOffset(x = it.width, y = -0) }
          )
        }
      ) {
        FilterScreen()
      }
    }
  }
}
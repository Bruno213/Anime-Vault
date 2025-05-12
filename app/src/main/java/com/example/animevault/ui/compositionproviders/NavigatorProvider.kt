package com.offerwise.PanelApps.ui.compose.compositionproviders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.animevault.ui.navigation.AppNavigator
import com.example.animevault.ui.navigation.Behavior
import com.example.animevault.ui.navigation.Navigator
import com.example.animevault.ui.navigation.Routes
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

val LocalNavigator = compositionLocalOf {
  @Suppress("USELESS_CAST")
  Navigator() as AppNavigator
}

@Composable
fun ProvideNavigator(
  navController: NavHostController,
  content: @Composable ()-> Unit
) {

  val context = LocalContext.current

  val navigator by remember {
    mutableStateOf(Navigator())
  }

//  BackHandler(
//    enabled = true
//  ) {
//    if(navController.previousBackStackEntry == null && sharedViewModel.leaveApp) {
//      context.finish()
//    }
//
//    if(navController.previousBackStackEntry == null && !sharedViewModel.leaveApp) {
//      Toast.makeText(context, context.getString(R.string.exit_app), Toast.LENGTH_SHORT).show()
//      sharedViewModel.leaveApp = true
//    }
//  }

  LaunchedEffect(key1 = "navigation") {
    navigator.navigatorDispatcherFlow.onEach {
      if(it.first == Routes.NavigateBack.route) {
//        if(navController.previousBackStackEntry == null && !sharedViewModel.leaveApp) {
//          Toast.makeText(context, context.getString(R.string.exit_app), Toast.LENGTH_SHORT).show()
//          sharedViewModel.leaveApp = true
//        }

        navController.popBackStack()
        return@onEach
      }

      navController.currentDestination?.let { current ->
        val navOptions: NavOptions? = when(it.second) {

          is Behavior.ReplacePrevious -> NavOptions
            .Builder()
            .setPopUpTo(current.route, true)
            .build()

          is Behavior.LaunchSingleTop -> NavOptions
            .Builder()
            .setLaunchSingleTop(singleTop = true)
            .build()

          is Behavior.PopUpTo -> NavOptions
            .Builder()
            .setPopUpTo((it.second as Behavior.PopUpTo).route, (it.second as Behavior.PopUpTo).inclusive)
            .build()

          else -> null
        }

        navController.navigate(
          route = it.first,
          navOptions = navOptions,
        )
      }
    }.launchIn(this)
  }

  CompositionLocalProvider(LocalNavigator provides navigator, content = content)
}
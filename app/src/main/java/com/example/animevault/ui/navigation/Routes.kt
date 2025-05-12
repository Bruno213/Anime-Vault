package com.example.animevault.ui.navigation

sealed class Routes(val route: String) {
  data object Splash: Routes("Splash")
  data object List: Routes("List")
  data object Details: Routes("Details")
  data object Filter: Routes("Filter")
  data object NavigateBack: Routes("NavigateBack")
}

enum class NavigationGraphs {
  MAIN,
}
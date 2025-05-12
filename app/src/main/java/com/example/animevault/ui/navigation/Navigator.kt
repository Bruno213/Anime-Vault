package com.example.animevault.ui.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface AppNavigator {
  fun navigateTo(route: String, behavior: Behavior? = null)
  fun navigateBack()
}

sealed class Behavior {
  data object ReplacePrevious: Behavior()
  data object LaunchSingleTop: Behavior()
  data class PopUpTo(
    val route: String,
    val inclusive: Boolean = true
  ): Behavior()
}

class Navigator: AppNavigator {

  private val _navigatorDispatcherFlow = MutableSharedFlow<Pair<String, Behavior?>>(extraBufferCapacity = 1)
  val navigatorDispatcherFlow = _navigatorDispatcherFlow.asSharedFlow()

  override fun navigateTo(
    route: String,
    behavior: Behavior?
  ) {
    _navigatorDispatcherFlow.tryEmit(Pair(route, behavior))
  }

  override fun navigateBack() {
    _navigatorDispatcherFlow.tryEmit(Pair(Routes.NavigateBack.route, null))
  }
}
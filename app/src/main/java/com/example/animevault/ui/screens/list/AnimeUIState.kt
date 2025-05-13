package com.example.animevault.ui.screens.list

import androidx.compose.runtime.Stable
import com.example.animevault.data.model.AniListMedia

@Stable
data class ListState(
  val items: List<AniListMedia> = listOf(),
  val isLoaded: Boolean = false
)

data class UIState(
  val currentPage: Int = 0,
  val hasNextPage: Boolean = false,
  val list: ListState = ListState(),
  val filteredList: ListState = ListState(),
  val errorMessage: String = "",
  val selectedMedia: AniListMedia? = null
)
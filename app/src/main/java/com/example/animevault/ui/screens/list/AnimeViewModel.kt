package com.example.animevault.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animevault.data.model.AniListMedia
import com.example.animevault.data.model.Result
import com.example.animevault.data.repository.AnimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import kotlin.time.Duration

sealed class AnimeUIAction {
  data class SearchAnime(val query: String): AnimeUIAction()
  data object NextPage: AnimeUIAction()
  data object PreviousPage: AnimeUIAction()
  data object HideDetails: AnimeUIAction()
  data class ShowDetails(val animeMedia: AniListMedia): AnimeUIAction()
}

class AnimeViewModel(
  private val animeRepository: AnimeRepository
): ViewModel() {

  private val _uiState = MutableStateFlow(UIState())
  val uiState = _uiState.asStateFlow()

  init {
    getAnimeList(1)
  }

  fun onUIAction(uiAction: AnimeUIAction) {
    when(uiAction) {
      AnimeUIAction.NextPage -> getAnimeList(_uiState.value.currentPage+1)
      AnimeUIAction.PreviousPage -> getAnimeList(_uiState.value.currentPage-1)
      is AnimeUIAction.SearchAnime -> {
        _uiState.update {
          it.copy(
            filteredList = ListState(
              isLoaded = true,
              items = it.list.items.filter { item -> item.title.english.ifEmpty { item.title.native }.contains(uiAction.query) }
            )
          )
        }
      }
      is AnimeUIAction.ShowDetails -> _uiState.update {
        it.copy(selectedMedia = uiAction.animeMedia)
      }
      AnimeUIAction.HideDetails -> _uiState.update { it.copy(selectedMedia = null) }
    }
  }

  private fun getAnimeList(page: Int) {
    viewModelScope.launch {
      when(val result = animeRepository.getAnime(page)) {
        is Result.Success -> {
          result.data?.let { data ->
            _uiState.update { it.copy(
              hasNextPage = data.page.pageInfo.hasNextPage,
              currentPage = data.page.pageInfo.currentPage,
              list = ListState(
                items = data.page.media,
                isLoaded = true
              ),
            ) }
          }
        }

        is Result.Failure -> {
          _uiState.update {
            it.copy(errorMessage = result.message)
          }

          dismissError()
        }
      }
    }
  }

  private suspend fun dismissError() {
    delay(3000)
    _uiState.update { it.copy(errorMessage = "") }
  }
}
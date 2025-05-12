package com.example.animevault.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animevault.data.model.Result
import com.example.animevault.data.repository.AnimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class AnimeUIAction {
  data class SearchAnime(val query: String): AnimeUIAction()
  data object NextPage: AnimeUIAction()
  data object PreviousPage: AnimeUIAction()
}

class AnimeViewModel(
  private val animeRepository: AnimeRepository
): ViewModel() {

  private val _uiState = MutableStateFlow(UIState())
  val uiState = _uiState.asStateFlow()

  init {
    getAnimeList()
  }

  fun onUIAction(uiAction: AnimeUIAction) {
    when(uiAction) {
      AnimeUIAction.NextPage -> TODO()
      AnimeUIAction.PreviousPage -> TODO()
      is AnimeUIAction.SearchAnime -> TODO()
    }
  }

  private fun getAnimeList() {
    viewModelScope.launch {
      when(val result = animeRepository.getAnime()) {
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
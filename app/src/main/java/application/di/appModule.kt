package application.di

import com.example.animevault.data.repository.AnimeRepository
import com.example.animevault.data.repository.AnimeRepositoryImpl
import com.example.animevault.ui.screens.list.AnimeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  single<AnimeRepository> { AnimeRepositoryImpl(get()) }
  viewModel { AnimeViewModel(get()) }
}
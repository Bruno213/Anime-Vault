package application

import com.example.animevault.data.repository.AnimeRepository
import com.example.animevault.data.repository.AnimeRepositoryImpl
import com.example.animevault.ui.screens.list.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  single<AnimeRepository> { AnimeRepositoryImpl() }
  viewModel { ListViewModel(get()) }
}
package application

import android.app.Application
import application.di.appModule
import application.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class AVApplication: Application() {

  override fun onCreate() {
    super.onCreate()

    Timber.plant(Timber.DebugTree())

    startKoin {
      androidLogger()
      androidContext(this@AVApplication)
      modules(appModule, networkModule)
    }
  }
}
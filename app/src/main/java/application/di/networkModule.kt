package application.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.animevault.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
  single {
    OkHttpClient
      .Builder()
      .addInterceptor(HttpLoggingInterceptor().also {
        it.setLevel(HttpLoggingInterceptor.Level.BODY)
      })
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .build()
  }

  single {
    ApolloClient.Builder()
      .serverUrl(BuildConfig.ANI_LIST_API)
      .okHttpClient(get())
      .build()
  }
}
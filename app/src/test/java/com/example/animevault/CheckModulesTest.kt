package com.example.animevault

import application.di.appModule
import application.di.networkModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModulesTest: KoinTest {

  @OptIn(KoinExperimentalAPI::class)
  @Test
  fun verifyModules() {
    appModule.verify()
    networkModule.verify()
  }
}
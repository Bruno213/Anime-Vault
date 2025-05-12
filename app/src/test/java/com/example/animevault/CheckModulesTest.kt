package com.example.animevault

import application.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModulesTest: KoinTest {

  @OptIn(KoinExperimentalAPI::class)
  @Test
  fun verifyModules() {
    appModule.verify()
  }
}
package com.example.animevault.data.repository

import com.anilist.graphQL.GetAnimeListQuery
import com.apollographql.apollo.ApolloClient
import com.example.animevault.data.model.AniListData
import com.example.animevault.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

interface AnimeRepository {
  suspend fun getAnime(page: Int): Result<AniListData>
}

class AnimeRepositoryImpl(
  private val apolloClient: ApolloClient
): AnimeRepository {

  override suspend fun getAnime(page: Int) = withContext(Dispatchers.IO) {
    val response = apolloClient.query(GetAnimeListQuery(page)).execute()
    Timber.tag("ApolloResponseError").d(response.exception)

    if(!response.exception?.message.isNullOrEmpty()) {
      return@withContext Result.Failure(message = response.exception!!.message!!)
    }

    if(response.errors?.isNotEmpty() == true) {
      val issues = response.errors!!.joinToString(",")

      Timber.tag("ApolloResponseError").d("Schema Errors: $issues.")
      return@withContext Result.Failure("Failed due to invalid schema values: $issues.")
    }

    response.data?.let { data ->
      return@withContext Result.Success(AniListData.from(data))
    }

    return@withContext Result.Failure("Something went wrong.")
  }
}
package com.example.animevault.data.model

import com.anilist.graphQL.GetAnimeListQuery


data class AniListData(
  val page: AniListPage
) {
  companion object {
    fun from(data: GetAnimeListQuery.Data) =
      AniListData(
        page = AniListPage(
          AniListPageInfo(
            currentPage = data.Page.pageInfo.currentPage,
            last = data.Page.pageInfo.lastPage,
            perPage = data.Page.pageInfo.lastPage,
            hasNextPage = data.Page.pageInfo.hasNextPage,
          ),
          media = data.Page.media
            .filterNotNull()
            .map { media ->
              AniListMedia(
                id = media.id,
                status = media.status.name,
                title = AniMediaTitle(
                  english = media.title.english ?: "",
                  native = media.title.native
                ),
                format = media.format.name,
                genres = media.genres.filterNotNull(),
                siteUrl = media.siteUrl,
                updatedAt = media.updatedAt,
                description = media.description,
                averageScore = media.averageScore,
                coverImage = AniListCoverImage(media.coverImage.large),
              )
            }
        )
      )
  }
}

data class AniListPage(
  val pageInfo: AniListPageInfo,
  val media: List<AniListMedia>,
)

data class AniListPageInfo(
  val perPage: Int,
  val currentPage: Int,
  val last: Int,
  val hasNextPage: Boolean
)

data class AniListMedia(
  val id: Int,
  val status: String,
  val format: String,
  val averageScore: Int,
  val genres: List<String>,
  val siteUrl: String,
  val title: AniMediaTitle,
  val coverImage: AniListCoverImage,
  val description: String,
  val updatedAt: Int
)

data class AniMediaTitle(
  val english: String,
  val native: String
)

data class AniListCoverImage(
  val large: String
)
package com.example.animevault.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.animevault.R
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.components.ErrorCard
import com.example.animevault.ui.components.SearchInput
import com.example.animevault.ui.compositionproviders.AppDimens
import com.example.animevault.ui.theme.AppPreviews
import com.example.animevault.ui.theme.Blue600
import com.example.animevault.ui.theme.Gray400
import com.example.animevault.ui.theme.Green400
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun AnimeScreen(viewModel: AnimeViewModel = koinViewModel()) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  Content(uiState = uiState)
}

@Composable
private fun Content(
  uiState: UIState
) {
  var searchQuery by remember { mutableStateOf("") }

  Box(
    Modifier
      .fillMaxSize()
      .background(color = Blue600)
      .padding(WindowInsets.navigationBars.asPaddingValues()),
    contentAlignment = Alignment.BottomCenter
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      Row(
        modifier = Modifier
          .padding(vertical = 30.dp)
          .widthIn(max = 620.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.Bottom
      ) {
        Image(
          modifier = Modifier.size(62.dp),
          painter = painterResource(R.drawable.logo),
          contentDescription = null
        )

        SearchInput(
          modifier = Modifier.weight(1f),
          query = searchQuery,
          onQueryChange = { searchQuery = it }
        )
      }

      LazyVerticalGrid(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        state = rememberLazyGridState(),
        columns = GridCells.Adaptive(minSize = 120.dp),
      ) {
        items(uiState.list.items, key =  { it.id }) {
          Column(
            modifier = Modifier.animateItem(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            AsyncImage(
              modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp)),
              model = it.coverImage.large,
              placeholder = painterResource(R.drawable.ic_launcher_background),
              onError = {
                Timber.d("imageLoadError: ${it.result.throwable.message}")
              },
              contentDescription = "Image $it",
              contentScale = ContentScale.FillHeight
            )

            Text(
              it.title.english.ifEmpty { it.title.native },
              color = Color.White,
              fontSize = AppDimens.fontSizes.LG,
              fontWeight = FontWeight.Medium,
              textAlign = TextAlign.Center,
              overflow = TextOverflow.Ellipsis,
              maxLines = 2
            )
          }
        }
      }

      Row(
        modifier = Modifier
          .background(color = Color.White, CircleShape)
          .padding(vertical = 8.dp, horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          enabled = uiState.currentPage > 1,
          onClick = {}
        ) {
          Icon(
            modifier = Modifier.padding(end = 10.dp),
            painter = painterResource(R.drawable.back_arrow_navigation_svgrepo_com),
            contentDescription = null,
            tint = Gray400
          )
        }

        Text(
          "Page ${uiState.currentPage}",
          color = Gray400,
          fontSize = AppDimens.fontSizes.LG,
          fontWeight = FontWeight.Medium,
          textAlign = TextAlign.Center,
          overflow = TextOverflow.Ellipsis,
          maxLines = 2
        )

        IconButton(
          enabled = uiState.hasNextPage,
          onClick = {}
        ) {
          Icon(
            modifier = Modifier.padding(start = 10.dp)
              .rotate(180f),
            painter = painterResource(R.drawable.back_arrow_navigation_svgrepo_com),
            contentDescription = null,
            tint = Gray400
          )
        }
      }
    }
  }

  ErrorCard(isVisible = uiState.errorMessage.isNotEmpty(), description = uiState.errorMessage)

  AnimeDetailsView(uiState)
}

@Composable
private fun AnimeDetailsView(uiState: UIState) {
  AnimatedVisibility(
    visible = uiState.selectedMedia != null,
    label = "DetailsView"
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(color = Blue600)
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      uiState.selectedMedia?.let {
        AsyncImage(
          modifier = Modifier
            .widthIn(max = 360.dp)
            .fillMaxWidth()
            .border(width = 1.5.dp, color = Green400, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
          model = it.coverImage.large,
          contentDescription = null,
          placeholder = painterResource(R.drawable.logo),
          error = painterResource(R.drawable.test),
          contentScale = ContentScale.FillWidth
        )

        Text(
          "86: Eighty Six",
          color = Color.White,
          fontSize = AppDimens.fontSizes.LG,
          fontWeight = FontWeight.SemiBold,
          textAlign = TextAlign.Center,
          overflow = TextOverflow.Ellipsis,
          maxLines = 2
        )
      }
    }
  }
}

@AppPreviews
@Composable
fun ListScreenPreview() {
  AppThemePreview {
    Content(uiState = UIState(currentPage = 1, hasNextPage = true))
  }
}
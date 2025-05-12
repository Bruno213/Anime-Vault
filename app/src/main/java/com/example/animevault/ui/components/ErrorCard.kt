package com.example.animevault.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.animevault.R
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.compositionproviders.AppDimens
import com.example.animevault.ui.theme.AppPreviews
import com.example.animevault.ui.theme.Blue600
import com.example.animevault.ui.theme.PoppinsFontFamily

@Composable
fun ErrorCard(
  modifier: Modifier = Modifier,
  isVisible: Boolean,
  description: String
) {
  val screenHeight = LocalWindowInfo.current.containerSize.height

  val animatedOffset = animateOffsetAsState(
    targetValue = Offset(
      x = 0f,
      y = if (isVisible) 0f else screenHeight.toFloat()
    ),
    label = "ErrorCard"
  )

  AnimatedVisibility(
    visible = isVisible,
    enter = fadeIn(),
    exit = fadeOut()
  ) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(WindowInsets.navigationBars.asPaddingValues())
      .padding(bottom = 20.dp),
    verticalArrangement = Arrangement.Bottom
  ) {
    Row(
      modifier = modifier
        .offset {
          IntOffset(
            x = 0,
            y = animatedOffset.value.y.toInt()
          )
        }
        .shadow(
          elevation = 2.dp,
          spotColor = Blue600,
          ambientColor = Blue600,
          shape = RoundedCornerShape(20f)
        )
        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
        .border(1.5.dp, Color(0xFFF89D9D), shape = RoundedCornerShape(20.dp))
        .padding(14.dp),
      horizontalArrangement = Arrangement.spacedBy(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        modifier = Modifier
          .size(34.dp)
          .padding(6.dp),
        painter = painterResource(id = R.drawable.alert_2_svgrepo_com),
        contentDescription = null,
        tint = Color(0xFFF89D9D)
      )

      Text(
        text = description,
        color = Blue600,
        fontSize = AppDimens.fontSizes.XS,
        lineHeight = AppDimens.fontSizes.XS,
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
      )
    }
  }
  }

}

@AppPreviews
@Composable
fun ErrorCardPreview() {
  AppThemePreview {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      ErrorCard(
        isVisible = true,
        description = "Lorem ipsum dolor sit amet consectetur. In elementum nunc consectetur montes tincidunt vel id neque laoreet. Commodo diam commodo egestas interdum. Dictum magna quam vitae adipiscing in."
      )
    }
  }
}
package com.example.animevault.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.compositionproviders.AppDimens
import com.example.animevault.ui.theme.AppPreviews
import com.example.animevault.ui.theme.Gray400
import com.example.animevault.ui.theme.PoppinsFontFamily

@Composable
fun InputHint(text: String) {
  Text(
    text = text,
    color = Gray400,
    fontSize = AppDimens.fontSizes.XS,
    lineHeight = AppDimens.fontSizes.XS,
    fontWeight = FontWeight.Normal,
    fontFamily = PoppinsFontFamily,
  )
}

@AppPreviews
@Composable
fun InputHintPreview() {
  AppThemePreview {
    Column(modifier = Modifier.padding(20.dp)) {
      InputHint(text = "test")
    }
  }
}
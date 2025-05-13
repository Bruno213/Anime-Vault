 package com.example.animevault.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animevault.R
import com.example.animevault.ui.AppThemePreview
import com.example.animevault.ui.compositionproviders.AppDimens
import com.example.animevault.ui.theme.AppPreviews
import com.example.animevault.ui.theme.Blue1100
import com.example.animevault.ui.theme.Gray400
import com.example.animevault.ui.theme.Green400
import com.example.animevault.ui.theme.PoppinsFontFamily


 @Composable
fun SearchInput(
  modifier: Modifier = Modifier,
  query: String,
  onQueryChange: (String)-> Unit,
  hideBorder: Boolean = false,
  shape: Shape = MaterialTheme.shapes.extraLarge,
  elevation: Dp = 0.dp
) {

  val borderMod = if(!hideBorder) Modifier.border(
    1.5.dp,
    Green400,
    shape = shape
  ) else Modifier

   BasicTextField(
     value = query,
     onValueChange = onQueryChange,
     singleLine = true,
     cursorBrush = Brush.linearGradient(listOf(Green400, Green400)),

     keyboardOptions = KeyboardOptions(
       keyboardType = KeyboardType.Text,
       imeAction = ImeAction.Search,
       autoCorrect = false
     ),

     textStyle = TextStyle(
       color = Gray400,
       fontSize = AppDimens.fontSizes.XS,
       lineHeight = AppDimens.fontSizes.XS,
       fontFamily = PoppinsFontFamily,
       fontWeight = FontWeight.Normal,
     ),

     decorationBox = {
       Row(
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.spacedBy(8.dp),
         modifier = Modifier.padding(start = 16.dp)
       ) {
         Box(
           contentAlignment = Alignment.CenterStart,
         ) {
           if(query.isEmpty()) {
             InputHint("Search")
           }

           it()
         }
       }
     },

     modifier = modifier
       .widthIn(max = 620.dp)
       .fillMaxWidth()
       .height(height = 40.dp)
       .then(borderMod)
       .shadow(
         elevation = elevation,
         shape = shape,
         ambientColor = Blue1100,
         spotColor = Blue1100
       )
       .background(Color.White, shape)
   )
}

 @AppPreviews
 @Composable
fun SearchInputPreview() {
  AppThemePreview {
    Row(modifier = Modifier.padding(10.dp)) {
      SearchInput(query = "", onQueryChange = {})
    }
  }
}
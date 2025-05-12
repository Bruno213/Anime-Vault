package com.example.animevault.ui.theme

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextDimens(
  /** compactDimens: 32.sp
   *
   * mediumDimens: 32.sp */
  val XXXL: TextUnit,
  /** compactDimens: 28.sp
   *
   * mediumDimens: 28.sp */
  val XXL: TextUnit,
  /** compactDimens: 26.sp
   *
   * mediumDimens: 26.sp */
  val XL: TextUnit,
  /** compactDimens: 24.sp
   *
   * mediumDimens: 24.sp */
  val LG: TextUnit,
  /** compactDimens: 20.sp
   *
   * mediumDimens: 22.sp */
  val MD: TextUnit,
  /** compactDimens: 18.sp
   *
   * mediumDimens: 20.sp */
  val SM: TextUnit,
  /** compactDimens: 16.sp
   *
   * mediumDimens: 18.sp */
  val XS: TextUnit,
  /** compactDimens: 14.sp
   *
   * mediumDimens: 16.sp */
  val XXS: TextUnit,
  /** compactDimens: 12.sp
   *
   * mediumDimens: 14.sp */
  val XXXS: TextUnit,
  /** compactDimens: 10.sp
   *
   * mediumDimens: 12.sp */
  val XXXXS: TextUnit,
) {
  companion object {

    val compactDimens = TextDimens(
      XXXL = 32.sp,
      XXL = 28.sp,
      XL = 26.sp,
      LG = 24.sp,
      MD = 20.sp,
      SM = 18.sp,
      XS = 16.sp,
      XXS = 14.sp,
      XXXS = 12.sp,
      XXXXS = 10.sp,
    )
    val mediumDimens = TextDimens(
      XXXL = 32.sp,
      XXL = 28.sp,
      XL = 26.sp,
      LG = 24.sp,
      MD = 22.sp,
      SM = 20.sp,
      XS = 18.sp,
      XXS = 16.sp,
      XXXS = 14.sp,
      XXXXS = 12.sp,
    )
  }
}

fun TextUnit.plus(value: Int): TextUnit {
  return (this.value.toInt() + value).sp
}

fun TextUnit.minus(value: Int): TextUnit {
  return (this.value.toInt() - value).sp
}
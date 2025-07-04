package org.sopt.certi.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// Default Color
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val TransParent = Color(0x00FF0000)

// Main Color
val MainColor500 = Color(0xFF456FFE)
val MainColor400 = Color(0xFF6588FF)
val MainColor300 = Color(0xFF91C3FF)
val MainColor200 = Color(0xFFE2E7FD)
val MainColor100 = Color(0xFFE0EEFF)
val MainColor50 = Color(0xFFF7F8FE)
val MainColor0 = Color(0xFFFBFDFF)

// Sub Color
val SubColor100 = Color(0xFFFFDB39)

// Gray Scale
val Gray600 = Color(0xFF434343)
val Gray500 = Color(0xFF595959)
val Gray400 = Color(0xFF8C8C8C)
val Gray300 = Color(0xFFBFBFBF)
val Gray200 = Color(0xFFD9D9D9)
val Gray100 = Color(0xFFF0F0F0)
val Gray0 = Color(0xFFF8F8F8)

// Background Color
val BackgroundColor = Color(0xD9000000)
val BackgroundCardColor = Color(0xB3000000)

@Immutable
data class CertiColors(
    val white: Color = White,
    val black: Color = Black,
    val transParent: Color = TransParent,

    val mainColor500: Color = MainColor500,
    val mainColor400: Color = MainColor400,
    val mainColor300: Color = MainColor300,
    val mainColor200: Color = MainColor200,
    val mainColor100: Color = MainColor100,
    val mainColor50: Color = MainColor50,
    val mainColor0: Color = MainColor0,

    val subColor100: Color = SubColor100,

    val gray600: Color = Gray600,
    val gray500: Color = Gray500,
    val gray400: Color = Gray400,
    val gray300: Color = Gray300,
    val gray200: Color = Gray200,
    val gray100: Color = Gray100,
    val gray0: Color = Gray0,

    val backgroundColor: Color = BackgroundColor,
    val backgroundCardColor: Color = BackgroundCardColor
)

val defaultCertiColors = CertiColors(
    white = White,
    black = Black,
    transParent = TransParent,

    mainColor500 = MainColor500,
    mainColor400 = MainColor400,
    mainColor300 = MainColor300,
    mainColor200 = MainColor200,
    mainColor100 = MainColor100,
    mainColor50 = MainColor50,
    mainColor0 = MainColor0,

    subColor100 = SubColor100,

    gray600 = Gray600,
    gray500 = Gray500,
    gray400 = Gray400,
    gray300 = Gray300,
    gray200 = Gray200,
    gray100 = Gray100,
    gray0 = Gray0,

    backgroundColor = BackgroundColor,
    backgroundCardColor = BackgroundCardColor
)

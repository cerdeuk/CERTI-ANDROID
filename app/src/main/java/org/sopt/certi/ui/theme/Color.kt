package org.sopt.certi.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Default Color
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val TransParent = Color(0x00FF0000)

// Main Color
val MainBlue = Color(0xFF456FFE)
val PurpleBlue = Color(0xFF6588FF)
val SkyBlue = Color(0xFF91C3FF)
val LightPurple = Color(0xFFE2E7FD)
val LightBlue = Color(0xFFE0EEFF)
val PurpleWhite = Color(0xFFF7F8FE)
val BlueWhite = Color(0xFFFBFDFF)

// Sub Color
val SubYellow = Color(0xFFFFDB39)

// Gray Scale
val Gray600 = Color(0xFF434343)
val Gray500 = Color(0xFF595959)
val Gray400 = Color(0xFF8C8C8C)
val Gray300 = Color(0xFFBFBFBF)
val Gray200 = Color(0xFFD9D9D9)
val Gray100 = Color(0xFFF0F0F0)
val Gray0 = Color(0xFFF8F8F8)

// Background Color
val Black85 = Color(0xD9000000)
val Black70 = Color(0xB3000000)

@Immutable
data class CertiColors(
    val white: Color = White,
    val black: Color = Black,
    val transParent: Color = TransParent,

    val mainBlue: Color = MainBlue,
    val purpleBlue: Color = PurpleBlue,
    val skyBlue: Color = SkyBlue,
    val lightPurple: Color = LightPurple,
    val lightBlue: Color = LightBlue,
    val purpleWhite: Color = PurpleWhite,
    val blueWhite: Color = BlueWhite,

    val subYellow: Color = SubYellow,

    val gray600: Color = Gray600,
    val gray500: Color = Gray500,
    val gray400: Color = Gray400,
    val gray300: Color = Gray300,
    val gray200: Color = Gray200,
    val gray100: Color = Gray100,
    val gray0: Color = Gray0,

    val black85: Color = Black85,
    val black70: Color = Black70
)

val defaultCertiColors = CertiColors(
    white = White,
    black = Black,
    transParent = TransParent,

    mainBlue = MainBlue,
    purpleBlue = PurpleBlue,
    skyBlue = SkyBlue,
    lightPurple = LightPurple,
    lightBlue = LightBlue,
    purpleWhite = PurpleWhite,
    blueWhite = BlueWhite,

    subYellow = SubYellow,

    gray600 = Gray600,
    gray500 = Gray500,
    gray400 = Gray400,
    gray300 = Gray300,
    gray200 = Gray200,
    gray100 = Gray100,
    gray0 = Gray0,

    black85 = Black85,
    black70 = Black70
)

val LocalCertiColorsProvider = staticCompositionLocalOf { defaultCertiColors }

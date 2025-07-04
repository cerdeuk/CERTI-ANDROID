package org.sopt.certi.ui.theme

import android.R.attr.fontFamily
import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.certi.R


object PretendardFont {
    val Bold = FontFamily(Font(R.font.pretendard_bold))
    val SemiBold = FontFamily(Font(R.font.pretendard_semibold))
    val Regular = FontFamily(Font(R.font.pretendard_regular))
}

sealed interface TypographyTokens {

    @Immutable
    data class Title(
        val bold_24: TextStyle,
        val bold_22: TextStyle
    )

    @Immutable
    data class Subtitle(
        val bold_20: TextStyle,
        val semibold_20: TextStyle
    )

    @Immutable
    data class Body(
        val bold_18: TextStyle,
        val semibold_18: TextStyle,
        val bold_16: TextStyle,
        val semibold_16: TextStyle,
        val regular_16: TextStyle
    )

    @Immutable
    data class Caption(
        val bold_14: TextStyle,
        val semibold_14: TextStyle,
        val regular_14: TextStyle,
        val semibold_12: TextStyle,
        val regular_12: TextStyle,
        val semibold_10: TextStyle,
        val regular_10: TextStyle
    )
}

@Immutable
data class CertiTypography(
    val title: TypographyTokens.Title,
    val subtitle: TypographyTokens.Subtitle,
    val body: TypographyTokens.Body,
    val caption: TypographyTokens.Caption
)

val defaultCertiTypography = CertiTypography(
    title = TypographyTokens.Title(
        bold_24 = TextStyle(fontFamily = PretendardFont.Bold, fontSize = 24.sp),
        bold_22 = TextStyle(fontFamily = PretendardFont.Bold, fontSize = 22.sp)
    ),
    subtitle = TypographyTokens.Subtitle(
        bold_20 = TextStyle(fontFamily = PretendardFont.Bold, fontSize = 20.sp),
        semibold_20 = TextStyle(fontFamily = PretendardFont.SemiBold, fontSize = 20.sp)
    ),
    body = TypographyTokens.Body(
        bold_18 = TextStyle(fontFamily = PretendardFont.Bold, fontSize = 18.sp),
        semibold_18 = TextStyle(fontFamily = PretendardFont.SemiBold, fontSize = 18.sp),
        bold_16 = TextStyle(fontFamily = PretendardFont.Bold, fontSize = 16.sp),
        semibold_16 = TextStyle(fontFamily = PretendardFont.SemiBold, fontSize = 16.sp),
        regular_16 = TextStyle(fontFamily = PretendardFont.Regular, fontSize = 16.sp)
    ),
    caption = TypographyTokens.Caption(
        bold_14 = TextStyle(fontFamily = PretendardFont.Bold, fontSize = 14.sp),
        semibold_14 = TextStyle(fontFamily = PretendardFont.SemiBold, fontSize = 14.sp),
        regular_14 = TextStyle(fontFamily = PretendardFont.Regular, fontSize = 14.sp),
        semibold_12 = TextStyle(fontFamily = PretendardFont.SemiBold, fontSize = 12.sp),
        regular_12 = TextStyle(fontFamily = PretendardFont.Regular, fontSize = 12.sp),
        semibold_10 = TextStyle(fontFamily = PretendardFont.SemiBold, fontSize = 10.sp),
        regular_10 = TextStyle(fontFamily = PretendardFont.Regular, fontSize = 10.sp)
    )
)


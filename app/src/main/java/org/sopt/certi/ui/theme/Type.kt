package org.sopt.certi.ui.theme

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
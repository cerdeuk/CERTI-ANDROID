package org.sopt.certi.presentation.type

import androidx.compose.ui.graphics.Color
import org.sopt.certi.ui.theme.defaultCertiColors

enum class CertCardColorType(
    val certificationIndex: Int,
    val textColor: Color,
    val subTextColor: Color,
    val chipBackgroundColor: Color,
    val chipTextColor: Color
) {
    BLUE(
        certificationIndex = 2,
        textColor = defaultCertiColors.white,
        subTextColor = defaultCertiColors.white,
        chipBackgroundColor = defaultCertiColors.lightPurple,
        chipTextColor = defaultCertiColors.mainBlue
    ),
    SKYBLUE(
        certificationIndex = 0,
        textColor = defaultCertiColors.gray600,
        subTextColor = defaultCertiColors.mainBlue,
        chipBackgroundColor = defaultCertiColors.lightPurple,
        chipTextColor = defaultCertiColors.mainBlue
    ),
    WHITE(
        certificationIndex = 3,
        textColor = defaultCertiColors.gray600,
        subTextColor = defaultCertiColors.mainBlue,
        chipBackgroundColor = defaultCertiColors.skyBlue,
        chipTextColor = defaultCertiColors.purpleWhite
    ),
    YELLOW(
        certificationIndex = 1,
        textColor = defaultCertiColors.gray600,
        subTextColor = defaultCertiColors.mainBlue,
        chipBackgroundColor = defaultCertiColors.lightPurple,
        chipTextColor = defaultCertiColors.mainBlue
    );

    companion object {
        fun fromIndex(index: Int): CertCardColorType {
            return entries.find { it.certificationIndex == index } ?: SKYBLUE
        }
    }
}

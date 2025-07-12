package org.sopt.certi.presentation.type

import androidx.compose.ui.graphics.Color
import org.sopt.certi.ui.theme.defaultCertiColors

enum class CertCardColorType(
    val certificationIndex: Int,
    val textColor: Color,
    val chipBackgroundColor: Color,
    val chipTextColor: Color
) {
    SKYBLUE(
        certificationIndex = 0,
        textColor = defaultCertiColors.gray600,
        chipBackgroundColor = defaultCertiColors.lightPurple,
        chipTextColor = defaultCertiColors.mainBlue
    ),
    YELLOW(
        certificationIndex = 1,
        textColor = defaultCertiColors.gray600,
        chipBackgroundColor = defaultCertiColors.lightPurple,
        chipTextColor = defaultCertiColors.mainBlue
    ),
    BLUE(
        certificationIndex = 2,
        textColor = defaultCertiColors.white,
        chipBackgroundColor = defaultCertiColors.lightPurple,
        chipTextColor = defaultCertiColors.mainBlue
    ),
    WHITE(
        certificationIndex = 3,
        textColor = defaultCertiColors.gray600,
        chipBackgroundColor = defaultCertiColors.skyBlue,
        chipTextColor = defaultCertiColors.purpleWhite
    );

    companion object {
        fun fromIndex(index: Int): CertCardColorType {
            return entries.find { it.certificationIndex == index } ?: SKYBLUE
        }
    }
}

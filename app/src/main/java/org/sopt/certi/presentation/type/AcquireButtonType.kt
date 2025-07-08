package org.sopt.certi.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import org.sopt.certi.R
import org.sopt.certi.ui.theme.defaultCertiColors

enum class AcquireButtonType(
    @StringRes val buttonText: Int,
    val buttonTextColor: Color,
    val backgroundColor: Color,
    val pressedBackgroundColor: Color,
    val borderAvailable: Boolean
) {
    FINISH(
        buttonText = R.string.cert_detail_acquired_button_text,
        buttonTextColor = defaultCertiColors.white,
        backgroundColor = defaultCertiColors.purpleBlue,
        pressedBackgroundColor = defaultCertiColors.mainBlue,
        borderAvailable = false
    ),
    EXPECTED(
        buttonText = R.string.cert_detail_acquire_expected_button_text,
        buttonTextColor = defaultCertiColors.purpleBlue,
        backgroundColor = defaultCertiColors.blueWhite,
        pressedBackgroundColor = defaultCertiColors.lightBlue,
        borderAvailable = true
    )
}

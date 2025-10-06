package org.sopt.certi.presentation.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import org.sopt.certi.R
import org.sopt.certi.ui.theme.defaultCertiColors

enum class NickNameValidType(
    @StringRes val stringRes: Int,
    val color: Color = defaultCertiColors.error
) {
    IDLE(
        stringRes = R.string.onboarding_nickname_textfield_idle,
        color = defaultCertiColors.gray300
    ),
    FOCUS(
        stringRes = R.string.onboarding_nickname_textfield_idle,
        color = defaultCertiColors.gray500
    ),
    VALID(
        stringRes = R.string.onboarding_nickname_textfield_valid,
        color = defaultCertiColors.mainBlue
    ),
    DUPLICATE(
        stringRes = R.string.onboarding_nickname_textfield_duplicate
    ),
    EMPTY(
        stringRes = R.string.onboarding_nickname_textfield_empty
    ),
    INVALID(
        stringRes = R.string.onboarding_nickname_textfield_invalid
    )
}
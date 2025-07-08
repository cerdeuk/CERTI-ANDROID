package org.sopt.certi.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import org.sopt.certi.R
import org.sopt.certi.ui.theme.defaultCertiColors

enum class SocialLoginType(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val backgroundColor: Color
) {
    KAKAO(
        label = R.string.login_kakao_btn,
        icon = R.drawable.img_kakao_login,
        backgroundColor = defaultCertiColors.subYellow
    ),
    GOOGLE(
        label = R.string.login_google_btn,
        icon = R.drawable.img_google_login,
        backgroundColor = defaultCertiColors.white
    )
}

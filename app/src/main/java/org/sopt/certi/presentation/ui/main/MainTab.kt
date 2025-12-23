package org.sopt.certi.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.sopt.certi.R
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.ui.theme.defaultCertiColors

enum class MainTab(
    @DrawableRes val defaultIconResId: Int,
    @StringRes val label: Int,
    val defaultIconColor: Color = defaultCertiColors.gray400,
    val selectedIconColor: Color = defaultCertiColors.mainBlue,
    val route: MainTabRoute
) {
    HOME(
        defaultIconResId = R.drawable.ic_home_24,
        label = R.string.main_bottom_navbar_home,
        route = MainTabRoute.Home
    ),
    CERTLIST(
        defaultIconResId = R.drawable.ic_category_24,
        label = R.string.main_bottom_navbar_certification,
        route = MainTabRoute.CertList
    ),
    RESUME(
        defaultIconResId = R.drawable.ic_resume_24,
        label = R.string.main_bottom_navbar_resume,
        route = MainTabRoute.Resume
    ),
    MYPAGE(
        defaultIconResId = R.drawable.ic_person_24,
        label = R.string.main_bottom_navbar_mypage,
        route = MainTabRoute.MyPage
    );

    companion object {
        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}

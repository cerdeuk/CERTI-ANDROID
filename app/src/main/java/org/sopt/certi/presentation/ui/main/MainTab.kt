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
    val defaultIconColor: Color = defaultCertiColors.gray600,
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
        label = R.string.main_bottom_navbar_category,
        route = MainTabRoute.CertList
    ),
    CERTRECOMMEND(
        defaultIconResId = R.drawable.ic_recommendation_24,
        label = R.string.main_bottom_navbar_recommend,
        route = MainTabRoute.CertRecommend
    ),
    RESUME(
        defaultIconResId = R.drawable.ic_resume_24,
        label = R.string.main_bottom_navbar_resume,
        route = MainTabRoute.Resume
    );

    companion object {
        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}

package org.sopt.certi.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import org.sopt.certi.R
import org.sopt.certi.core.navigation.MainTabRoute

enum class MainTab(
    @DrawableRes val defaultIconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    val route: MainTabRoute
) {
    HOME(
        defaultIconResId = R.drawable.ic_launcher_foreground,
        selectedIconResId = R.drawable.ic_launcher_background,
        route = MainTabRoute.Home
    ),
    CERTLIST(
        defaultIconResId = R.drawable.ic_launcher_foreground,
        selectedIconResId = R.drawable.ic_launcher_background,
        route = MainTabRoute.CertList
    ),
    CERTRECOMMEND(
        defaultIconResId = R.drawable.ic_launcher_foreground,
        selectedIconResId = R.drawable.ic_launcher_background,
        route = MainTabRoute.CertRecommend
    ),
    RESUME(
        defaultIconResId = R.drawable.ic_launcher_foreground,
        selectedIconResId = R.drawable.ic_launcher_background,
        route = MainTabRoute.Resume
    );

    companion object {
        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}
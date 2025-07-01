package org.sopt.certi.presentation.ui.splash.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.Route.Splash
import org.sopt.certi.presentation.ui.splash.SplashRoute

fun NavGraphBuilder.splashNavGraph(
    padding: PaddingValues,
    onNavigateToLogin: () -> Unit
) {
    composable<Splash> {
        SplashRoute(
            padding = padding,
            navigateToLogin = onNavigateToLogin
        )
    }
}
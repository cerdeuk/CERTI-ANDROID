package org.sopt.certi.presentation.ui.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.Route.Login
import org.sopt.certi.presentation.ui.login.LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues,
    onNavigateToOnBoarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<Login> {
        LoginRoute(
            padding = padding,
            navigateToOnBoarding = onNavigateToOnBoarding,
            navigateToHome = onNavigateToHome
        )
    }
}
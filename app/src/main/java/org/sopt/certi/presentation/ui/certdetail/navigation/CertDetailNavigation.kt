package org.sopt.certi.presentation.ui.certdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.CertRecommendRoute
import org.sopt.certi.presentation.ui.certdetail.CertDetailRoute
import org.sopt.certi.presentation.ui.main.MainTab
import org.sopt.certi.presentation.ui.resume.navigation.navigateToResume

fun NavController.navigateToCertDetail() {
    navigate(CertRecommendRoute.CertDetail)
}

fun NavGraphBuilder.certDetailNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<CertRecommendRoute.CertDetail> {
        val navOptions = navOptions {
            popUpTo(MainTab.RESUME.route) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        CertDetailRoute(
            padding = padding,
            navigateToResume = { navController.navigateToResume(navOptions) }
        )
    }
}

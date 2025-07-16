package org.sopt.certi.presentation.ui.certdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.CertRecommendRoute
import org.sopt.certi.presentation.ui.certdetail.CertDetailRoute
import org.sopt.certi.presentation.ui.main.MainTab
import org.sopt.certi.presentation.ui.resume.navigation.navigateToResume

fun NavController.navigateToCertDetail(certId: Long) {
    navigate(CertRecommendRoute.CertDetail.certDetailRoute(certId = certId))
}

fun NavGraphBuilder.certDetailNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable(
        route = CertRecommendRoute.CertDetail.DETAIL_ROUTE,
        arguments = listOf(navArgument("certId") { type = NavType.LongType })
    ) { backStackEntry ->
        val certId = backStackEntry.arguments?.getLong("certId") ?: 0L

        val navOptions = navOptions {
            popUpTo(MainTab.RESUME.route) { inclusive = false }
            launchSingleTop = true
            restoreState = true
        }

        CertDetailRoute(
            certId = certId,
            padding = padding,
            navigateToResume = {
                navController.navigateToResume(navOptions)
            }
        )
    }
}

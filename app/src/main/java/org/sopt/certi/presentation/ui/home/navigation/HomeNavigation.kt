package org.sopt.certi.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.CertRecommendRoute
import org.sopt.certi.core.navigation.HomeRoute
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail
import org.sopt.certi.presentation.ui.certrecommend.CertRecommendRoute
import org.sopt.certi.presentation.ui.certrecommend.navigation.navigateToCertRecommend
import org.sopt.certi.presentation.ui.home.HomeRoute
import org.sopt.certi.presentation.ui.precertificationedit.PreCertificationEditRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavController.navigateToPreCerti() {
    navigate(HomeRoute.CertPlanned)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            padding = padding,
            navigateToCertRecommend = { navController.navigateToCertRecommend( NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setRestoreState(true)
                .build()) },
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            },
            navigateToPreCerti = { navController.navigateToPreCerti() }
        )
    }

    composable<HomeRoute.CertPlanned> {
        PreCertificationEditRoute(
            padding = padding
        )
    }

    composable<HomeRoute.CertRecommended> {
        CertRecommendRoute(
            padding = padding,
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            }
        )
    }
}

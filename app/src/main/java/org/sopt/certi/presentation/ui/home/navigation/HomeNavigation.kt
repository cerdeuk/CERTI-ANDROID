package org.sopt.certi.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.HomeRoute
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail
import org.sopt.certi.presentation.ui.home.HomeRoute
import org.sopt.certi.presentation.ui.login.LoginRoute
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
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            },
            navigateToPreCerti = { navController.navigateToPreCerti() },
        )
    }

    composable<HomeRoute.CertPlanned> {
        PreCertificationEditRoute(
            padding = padding
        )
    }
}

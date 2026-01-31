package org.sopt.certi.presentation.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.HomeRoute
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail
import org.sopt.certi.presentation.ui.certlist.navigation.navigateToCertList
import org.sopt.certi.presentation.ui.home.HomeRoute
import org.sopt.certi.presentation.ui.main.MainTab
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
            navigateToCertTab = {
                val navOptions = navOptions {
                    popUpTo(MainTab.HOME.route) {
                        inclusive = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                navController.navigateToCertList(navOptions)
            },
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            }
        )
    }

    composable<HomeRoute.CertPlanned> {
        PreCertificationEditRoute(
            padding = padding
        )
    }
}

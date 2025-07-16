package org.sopt.certi.presentation.ui.certlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail
import org.sopt.certi.presentation.ui.certlist.CertListRoute
import org.sopt.certi.presentation.ui.search.navigation.navigateToSearch

fun NavController.navigateToCertList(navOptions: NavOptions) {
    navigate(MainTabRoute.CertList, navOptions)
}

fun NavGraphBuilder.certListNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.CertList> {
        CertListRoute(
            padding = padding,
            navigateToSearch = { navController.navigateToSearch() },
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            }
        )
    }
}

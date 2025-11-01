package org.sopt.certi.presentation.ui.trackcategorycertlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.CertListRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail
import org.sopt.certi.presentation.ui.trackcategorycertlist.TrackCategoryCertListRoute

fun NavController.navigateToTrackCategoryCertList() {
    navigate(CertListRoute.TrackCategoryCertList)
}

fun NavGraphBuilder.trackCategoryCertListNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<CertListRoute.TrackCategoryCertList> {
        TrackCategoryCertListRoute(
            padding = padding,
            navigateToSearch = { navController.navigate(CertListRoute.Search) },
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            }
        )
    }
}

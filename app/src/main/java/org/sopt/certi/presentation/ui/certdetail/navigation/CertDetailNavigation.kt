package org.sopt.certi.presentation.ui.certdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.CertRecommendRoute
import org.sopt.certi.presentation.ui.certdetail.CertDetailRoute

fun NavController.navigateToCertDetail() {
    navigate(CertRecommendRoute.CertDetail)
}

fun NavGraphBuilder.certDetailNavGraph(
    padding: PaddingValues
) {
    composable<CertRecommendRoute.CertDetail> {
        CertDetailRoute(
            padding = padding
        )
    }
}

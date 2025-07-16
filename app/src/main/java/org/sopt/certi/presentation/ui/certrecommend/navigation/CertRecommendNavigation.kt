package org.sopt.certi.presentation.ui.certrecommend.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.certrecommend.CertRecommendRoute
import org.sopt.certi.core.navigation.CertRecommendRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail

fun NavController.navigateToCertRecommend(navOptions: NavOptions) {
    navigate(MainTabRoute.CertRecommend, navOptions)
}

fun NavGraphBuilder.certRecommendNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.CertRecommend> {
        CertRecommendRoute(
            padding = padding,
            navigateToCertDetail = { certId ->
                navController.navigateToCertDetail(certId = certId)
            }
        )
    }
}

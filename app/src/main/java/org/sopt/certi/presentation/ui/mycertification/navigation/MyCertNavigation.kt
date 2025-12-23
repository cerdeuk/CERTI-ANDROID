package org.sopt.certi.presentation.ui.mycertification.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MyPageRoute
import org.sopt.certi.presentation.ui.certdetail.navigation.navigateToCertDetail
import org.sopt.certi.presentation.ui.mycertification.MyCertRoute

fun NavGraphBuilder.myCertificationNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MyPageRoute.MyCertification> {
        MyCertRoute(
            padding = padding,
            navigateToCertDetail = navController::navigateToCertDetail
        )
    }
}

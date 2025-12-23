package org.sopt.certi.presentation.ui.mycertification.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MyPageRoute
import org.sopt.certi.presentation.ui.mycertification.MyCertRoute

fun NavController.navigateToEditCert() {
    navigate(MyPageRoute.EditMyCertification)
}

fun NavGraphBuilder.myCertificationNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MyPageRoute.MyCertification> {
        MyCertRoute(
            padding = padding,
            navigateToEditCert = navController::navigateToEditCert
        )
    }
}

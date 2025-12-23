package org.sopt.certi.presentation.ui.myacademicinfo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MyPageRoute
import org.sopt.certi.presentation.ui.myacademicinfo.AcademicInfoRoute
import org.sopt.certi.presentation.ui.myacademicinfo.EditUnivRoute

fun NavController.navigateToEditUniv() {
    navigate(MyPageRoute.EditUniv)
}

fun NavGraphBuilder.academicInfoNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MyPageRoute.AcademicInfo> {
        AcademicInfoRoute(
            padding = padding,
            navigateToEditUniv = navController::navigateToEditUniv
        )
    }

    composable<MyPageRoute.EditUniv> {
        EditUnivRoute(
            padding = padding
        )
    }
}

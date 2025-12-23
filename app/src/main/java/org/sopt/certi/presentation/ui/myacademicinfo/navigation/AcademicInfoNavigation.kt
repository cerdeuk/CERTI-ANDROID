package org.sopt.certi.presentation.ui.myacademicinfo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MyPageRoute
import org.sopt.certi.presentation.ui.myacademicinfo.AcademicInfoRoute
import org.sopt.certi.presentation.ui.myacademicinfo.EditMajorRoute
import org.sopt.certi.presentation.ui.myacademicinfo.EditUnivRoute

fun NavController.navigateToEditUniv() {
    navigate(MyPageRoute.EditUniv)
}

fun NavController.navigateToEditMajor() {
    navigate(MyPageRoute.EditMajor)
}

fun NavGraphBuilder.academicInfoNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MyPageRoute.AcademicInfo> {
        AcademicInfoRoute(
            padding = padding,
            navigateToEditUniv = navController::navigateToEditUniv,
            navigateToEditMajor = navController::navigateToEditMajor
        )
    }

    composable<MyPageRoute.EditUniv> {
        EditUnivRoute(
            padding = padding
        )
    }

    composable<MyPageRoute.EditMajor> {
        EditMajorRoute(
            padding = padding
        )
    }
}

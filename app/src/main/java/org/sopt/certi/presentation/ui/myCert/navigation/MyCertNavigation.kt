package org.sopt.certi.presentation.ui.myCert.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.ResumeRoute
import org.sopt.certi.presentation.ui.myCert.ResumeMyCertRoute

fun NavGraphBuilder.myCertNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<ResumeRoute.MyCert> {
        ResumeMyCertRoute(padding = padding)
    }
}

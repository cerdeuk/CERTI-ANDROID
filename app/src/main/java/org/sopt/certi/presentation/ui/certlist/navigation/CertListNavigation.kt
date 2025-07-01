package org.sopt.certi.presentation.ui.certlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.certlist.CertListRoute

fun NavController.navigateToCertList(navOptions: NavOptions) {
    navigate(MainTabRoute.CertList, navOptions)
}

fun NavGraphBuilder.certListNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.CertList> {
        CertListRoute(
            padding = padding
        )
    }
}
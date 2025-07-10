package org.sopt.certi.presentation.ui.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.CertListRoute
import org.sopt.certi.presentation.ui.search.SearchRoute

fun NavController.navigateToSearch() {
    navigate(CertListRoute.Search)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<CertListRoute.Search> {
        SearchRoute(
            padding = padding
        )
    }
}

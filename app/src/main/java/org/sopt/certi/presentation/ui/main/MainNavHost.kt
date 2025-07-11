package org.sopt.certi.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import org.sopt.certi.presentation.ui.certdetail.navigation.certDetailNavGraph
import org.sopt.certi.presentation.ui.certlist.navigation.certListNavGraph
import org.sopt.certi.presentation.ui.certrecommend.navigation.certRecommendNavGraph
import org.sopt.certi.presentation.ui.home.navigation.homeNavGraph
import org.sopt.certi.presentation.ui.login.navigation.loginNavGraph
import org.sopt.certi.presentation.ui.onboarding.OnBoardingViewModel
import org.sopt.certi.presentation.ui.onboarding.navigation.onBoardingNavGraph
import org.sopt.certi.presentation.ui.resume.navigation.resumeNavGraph
import org.sopt.certi.presentation.ui.search.navigation.searchNavGraph
import org.sopt.certi.presentation.ui.splash.navigation.splashNavGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()

        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            splashNavGraph(
                padding = padding,
                onNavigateToLogin = navigator::navigateToLogin
            )

            loginNavGraph(
                padding = padding,
                onNavigateToOnBoarding = navigator::navigateToOnBoarding,
                onNavigateToHome = navigator::navigateToHome
            )

            onBoardingNavGraph(
                padding = padding,
                onNavigateToHome = navigator::navigateToHome,
                navController = navigator.navController,
                viewModel = onBoardingViewModel
            )

            homeNavGraph(
                padding = padding
            )

            certListNavGraph(
                padding = padding,
                navController = navigator.navController
            )

            searchNavGraph(
                padding = padding,
                navController = navigator.navController
            )

            certRecommendNavGraph(
                padding = padding,
                navController = navigator.navController
            )

            certDetailNavGraph(padding = padding)

            resumeNavGraph(
                padding = padding,
                navController = navigator.navController
            )
        }
    }
}

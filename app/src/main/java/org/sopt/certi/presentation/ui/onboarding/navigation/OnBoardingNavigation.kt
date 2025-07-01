package org.sopt.certi.presentation.ui.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.OnBoardingRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingGradeRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingJobCategoryRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingMajorRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingInfoRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingTrackRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingUnivRoute
import org.sopt.certi.presentation.ui.onboarding.OnBoardingViewModel

fun NavController.navigateToOnBoarding() {
    navigate(OnBoardingRoute.Univ)
}

fun NavController.navigateToGrade() {
    navigate(OnBoardingRoute.Grade)
}

fun NavController.navigateToTrack() {
    navigate(OnBoardingRoute.Track)
}

fun NavController.navigateToMajor() {
    navigate(OnBoardingRoute.Major)
}

fun NavController.navigateToJobCategory() {
    navigate(OnBoardingRoute.JobCategory)
}

fun NavController.navigateToOnBoardingInfo() {
    navigate(OnBoardingRoute.OnBoardingInfo)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    onNavigateToHome: () -> Unit,
    navController: NavController,
    viewModel: OnBoardingViewModel
) {
    composable<OnBoardingRoute.Univ> {
        OnBoardingUnivRoute(
            padding = padding,
            navigateToGrade = navController::navigateToGrade,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Grade> {
        OnBoardingGradeRoute(
            padding = padding,
            navigateToTrack = navController::navigateToTrack,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Track> {
        OnBoardingTrackRoute(
            padding = padding,
            navigateToMajor = navController::navigateToMajor,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Major> {
        OnBoardingMajorRoute(
            padding = padding,
            navigateToJobCategory = navController::navigateToJobCategory,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.JobCategory> {
        OnBoardingJobCategoryRoute(
            padding = padding,
            navigateToOnBoardingInfo = navController::navigateToOnBoardingInfo,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.OnBoardingInfo> {
        OnBoardingInfoRoute(
            padding = padding,
            navigateToHome = onNavigateToHome,
            viewModel = viewModel
        )
    }
}
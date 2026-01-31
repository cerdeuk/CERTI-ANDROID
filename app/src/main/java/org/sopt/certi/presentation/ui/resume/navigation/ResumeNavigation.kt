package org.sopt.certi.presentation.ui.resume.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.core.navigation.ResumeRoute
import org.sopt.certi.presentation.ui.mypage.navigation.navigateToMyCertification
import org.sopt.certi.presentation.ui.mypage.navigation.navigateToMyPage
import org.sopt.certi.presentation.ui.resume.ResumeRoute

fun NavController.navigateToResume(navOptions: NavOptions) {
    navigate(MainTabRoute.Resume, navOptions)
}

fun NavController.navigateToWorkExperience() {
    navigate(ResumeRoute.WorkExperience)
}

fun NavController.navigateToActivities() {
    navigate(ResumeRoute.Activities)
}

fun NavGraphBuilder.resumeNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.Resume> {
        ResumeRoute(
            padding = padding,
            navigateToMyPage = {
                navController.navigateToMyPage(
                    navOptions {
                        popUpTo(MainTabRoute.Resume) {
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                )
            },
            navigateToMyCert = navController::navigateToMyCertification,
            navigateToWorkExperience = navController::navigateToWorkExperience,
            navigateToActivities = navController::navigateToActivities
        )
    }
}

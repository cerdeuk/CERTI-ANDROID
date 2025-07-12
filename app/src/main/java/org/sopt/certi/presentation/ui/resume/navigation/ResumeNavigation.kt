package org.sopt.certi.presentation.ui.resume.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.core.navigation.ResumeRoute
import org.sopt.certi.presentation.ui.resume.ResumeActivitiesRoute
import org.sopt.certi.presentation.ui.resume.ResumeAddActivitiesRoute
import org.sopt.certi.presentation.ui.resume.ResumeMyCertRoute
import org.sopt.certi.presentation.ui.resume.ResumeRoute
import org.sopt.certi.presentation.ui.resume.ResumeWorkExperienceRoute

fun NavController.navigateToResume(navOptions: NavOptions) {
    navigate(MainTabRoute.Resume, navOptions)
}

fun NavController.navigateToMyCert() {
    navigate(ResumeRoute.MyCert)
}

fun NavController.navigateToWorkExperience() {
    navigate(ResumeRoute.WorkExperience)
}

fun NavController.navigateToActivities() {
    navigate(ResumeRoute.Activities)
}

fun NavController.navigateToAddActivities() {
    navigate(ResumeRoute.AddActivities)
}

fun NavGraphBuilder.resumeNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.Resume> {
        ResumeRoute(
            padding = padding,
            navigateToMyCert = navController::navigateToMyCert,
            navigateToWorkExperience = navController::navigateToWorkExperience,
            navigateToActivities = navController::navigateToActivities
        )
    }

    composable<ResumeRoute.MyCert> {
        ResumeMyCertRoute(padding = padding)
    }

    composable<ResumeRoute.WorkExperience> {
        ResumeWorkExperienceRoute(padding = padding)
    }

    composable<ResumeRoute.Activities> {
        ResumeActivitiesRoute(
            padding = padding,
            onNavigateToAddActivities = navController::navigateToAddActivities
        )
    }

    composable<ResumeRoute.AddActivities> {
        ResumeAddActivitiesRoute(
            padding = padding,
            navigateToResume = {
                navController.navigateToResume(
                    navOptions {
                        popUpTo(MainTabRoute.Resume) { inclusive = true }
                    }
                )
            }
        )
    }
}

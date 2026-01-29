package org.sopt.certi.presentation.ui.activity.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.core.navigation.ResumeRoute
import org.sopt.certi.presentation.ui.activity.ResumeActivitiesRoute
import org.sopt.certi.presentation.ui.activity.ResumeAddActivitiesRoute
import org.sopt.certi.presentation.ui.activity.ResumeEditActivitiesRoute
import org.sopt.certi.presentation.ui.resume.navigation.navigateToResume

fun NavController.navigateToAddActivities() {
    navigate(ResumeRoute.AddActivities)
}

fun NavController.navigateToEditActivities(activityId: Long) {
    navigate(ResumeRoute.EditActivities(activityId))
}

fun NavGraphBuilder.activityNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<ResumeRoute.Activities> {
        ResumeActivitiesRoute(
            padding = padding,
            onNavigateToAddActivities = navController::navigateToAddActivities,
            onNavigateToEditActivities = navController::navigateToEditActivities
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

    composable<ResumeRoute.EditActivities> {
        ResumeEditActivitiesRoute(
            padding = padding,
            navigateToResume = {
                navController.navigateToResume(
                    navOptions { popUpTo(MainTabRoute.Resume) { inclusive = true } }
                )
            }
        )
    }
}

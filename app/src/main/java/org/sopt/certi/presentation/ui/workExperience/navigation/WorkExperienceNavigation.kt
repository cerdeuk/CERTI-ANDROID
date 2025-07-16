package org.sopt.certi.presentation.ui.workExperience.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.core.navigation.ResumeRoute
import org.sopt.certi.presentation.ui.resume.navigation.navigateToResume
import org.sopt.certi.presentation.ui.workExperience.ResumeAddWorkExperienceRoute
import org.sopt.certi.presentation.ui.workExperience.ResumeWorkExperienceRoute

fun NavController.navigateToAddWorkExperience() {
    navigate(ResumeRoute.AddWorkExperience)
}

fun NavGraphBuilder.workExperienceNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<ResumeRoute.WorkExperience> {
        ResumeWorkExperienceRoute(
            padding = padding,
            onNavigateToAddWordExperience = navController::navigateToAddWorkExperience
        )
    }

    composable<ResumeRoute.AddWorkExperience> {
        ResumeAddWorkExperienceRoute(
            padding = padding,
            onNavigateToResume = {
                navController.navigateToResume(
                    navOptions {
                        popUpTo(MainTabRoute.Resume) { inclusive = true }
                    }
                )
            }
        )
    }
}

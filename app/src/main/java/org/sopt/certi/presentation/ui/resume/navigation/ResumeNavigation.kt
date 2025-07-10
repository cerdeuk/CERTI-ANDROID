package org.sopt.certi.presentation.ui.resume.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.core.navigation.ResumeRoute
import org.sopt.certi.presentation.ui.resume.ResumeActivitiesRoute
import org.sopt.certi.presentation.ui.resume.ResumeMyCertRoute
import org.sopt.certi.presentation.ui.resume.ResumeRoute
import org.sopt.certi.presentation.ui.resume.ResumeWorkExperienceRoute

fun NavController.navigateToResume(navOptions: NavOptions) {
    navigate(MainTabRoute.Resume, navOptions)
}

fun NavGraphBuilder.resumeNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.Resume> {
        ResumeRoute(padding = padding)
    }

    composable<ResumeRoute.MyCert> {
        ResumeMyCertRoute(padding = padding)
    }

    composable<ResumeRoute.WorkExperience> {
        ResumeWorkExperienceRoute(padding = padding)
    }

    composable<ResumeRoute.Activities> {
        ResumeActivitiesRoute(padding = padding)
    }
}

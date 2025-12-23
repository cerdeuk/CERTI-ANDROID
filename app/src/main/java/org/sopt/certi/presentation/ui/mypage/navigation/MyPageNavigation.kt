package org.sopt.certi.presentation.ui.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MainTabRoute
import org.sopt.certi.presentation.ui.mypage.MyPageMainRoute

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    composable<MainTabRoute.MyPage> {
        MyPageMainRoute(
            padding = padding,
            navigateToPersonalInfo = {},
            navigateToSchoolInfo = {},
            navigateToCertManage = {},
            navigateToSetting = {},
            navigateToQuestion = {}
        )
    }
}

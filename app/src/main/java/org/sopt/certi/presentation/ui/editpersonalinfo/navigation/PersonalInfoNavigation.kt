package org.sopt.certi.presentation.ui.editpersonalinfo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.certi.core.navigation.MyPageRoute
import org.sopt.certi.presentation.ui.editpersonalinfo.EditPersonalInfoRoute

fun NavGraphBuilder.personalInfoGraph(
    padding: PaddingValues
) {
    composable<MyPageRoute.PersonalInfo> {
        EditPersonalInfoRoute(
            padding = padding
        )
    }
}

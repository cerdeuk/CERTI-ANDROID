package org.sopt.certi.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenHeightDp(percentage: Float): Dp {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    return screenHeight * percentage
}

@Composable
fun screenWidthDp(percentage: Float): Dp {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    return screenWidth * percentage
}

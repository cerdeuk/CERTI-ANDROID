package org.sopt.certi.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    MainScreenContent(
        navigator = navigator
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier.background(color = Color.White)
                    .navigationBarsPadding(),
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries,
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}
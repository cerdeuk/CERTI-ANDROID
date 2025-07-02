package org.sopt.certi.presentation.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun MainBottomBar(
    visible: Boolean,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
    tabs: List<MainTab>,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Surface(
            color = Color.White
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp * 0.071f).dp)
            ) {
                tabs.forEach { tab ->
                    MainBottomBarItem(
                        tab = tab,
                        selected = tab == currentTab,
                        onClick = { onTabSelected(tab) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .align(Alignment.CenterVertically)
            .weight(1f)
            .selectable(
                selected = selected,
                role = Role.Tab,
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = if (selected) ImageVector.vectorResource(tab.selectedIconResId) else ImageVector.vectorResource(tab.defaultIconResId),
            contentDescription = null,
            tint = if (selected) Color.Black else Color.Cyan,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainBottomBar() {
    CERTITheme {
        MainBottomBar(
            visible = true,
            currentTab = MainTab.HOME,
            onTabSelected = {},
            tabs = MainTab.entries,
            modifier = Modifier
        )
    }
}

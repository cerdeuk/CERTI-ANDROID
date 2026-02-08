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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

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
                modifier = modifier.fillMaxWidth()
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
            .weight(1f)
            .selectable(
                selected = selected,
                role = Role.Tab,
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(top = screenHeightDp(12.dp), bottom = screenHeightDp(4.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = tab.defaultIconResId),
            contentDescription = stringResource(tab.label),
            tint = if (selected) tab.selectedIconColor else tab.defaultIconColor,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = stringResource(id = tab.label),
            color = if (selected) tab.selectedIconColor else tab.defaultIconColor,
            style = CertiTheme.typography.caption.semibold_10
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

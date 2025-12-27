package org.sopt.certi.presentation.ui.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.section.ArrowMenuSection
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SettingRoute(modifier: Modifier = Modifier) {
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(screenWidthDp(20.dp))
    ) {
        Text(
            text = stringResource(R.string.other_settings),
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400
        )
        Spacer(modifier = Modifier.heightForScreenPercentage(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(36.dp))
        ) {
            ArrowMenuSection(
                text = stringResource(R.string.settings_notification),
                onClick = {}
            )

            ArrowMenuSection(
                text = stringResource(R.string.privacy_policy),
                onClick = {}
            )

            ArrowMenuSection(
                text = stringResource(R.string.delete_account),
                onClick = {}
            )

            ArrowMenuSection(
                text = stringResource(R.string.app_version),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    CERTITheme {
        SettingScreen()
    }
}

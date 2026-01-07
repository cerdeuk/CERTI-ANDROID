package org.sopt.certi.presentation.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.MyPageTopBar
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SettingNotificationRoute(padding: PaddingValues) {
}

@Composable
fun SettingNotificationScreen(
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MyPageTopBar(
            headerTitle = stringResource(R.string.settings_notification),
            modifier = Modifier.padding(vertical = screenHeightDp(24.dp))
        )

        Row(
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.setting_marketing),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.black
            )
            Spacer(modifier = Modifier.widthForScreenPercentage(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_question_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray300
            )
            Spacer(modifier = Modifier.weight(1f))

            Switch(
                checked = checked,
                onCheckedChange = onCheckChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = CertiTheme.colors.white,
                    checkedTrackColor = CertiTheme.colors.purpleBlue,
                    checkedBorderColor = Color.Unspecified,
                    uncheckedThumbColor = CertiTheme.colors.white,
                    uncheckedTrackColor = CertiTheme.colors.gray300,
                    uncheckedBorderColor = Color.Unspecified
                )
            )
        }

        Text(
            text = stringResource(R.string.setting_marketing_description),
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
        )

        Column {
            Row {  }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingNotificationPreview() {
    CERTITheme {
        var checked by remember { mutableStateOf(false) }
        SettingNotificationScreen(
            checked = checked,
            onCheckChange = { checked = it }
        )
    }
}

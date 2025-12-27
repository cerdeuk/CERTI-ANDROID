package org.sopt.certi.presentation.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.header.MyPageHeader
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SettingNotificationRoute(padding: PaddingValues) {

}

@Composable
fun SettingNotificationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MyPageHeader(
            headerTitle = stringResource(R.string.settings_notification),
            modifier = Modifier.padding(vertical = screenHeightDp(24.dp))
        )

        Row (
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.settings_marketing),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.black
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_question_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray300
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingNotificationPreview() {
    CERTITheme {
        SettingNotificationScreen()
    }
}
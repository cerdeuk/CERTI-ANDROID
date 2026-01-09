package org.sopt.certi.presentation.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.CertiDialog
import org.sopt.certi.core.component.topbar.MyPageTopBar
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.ui.setting.component.CustomCheckbox
import org.sopt.certi.presentation.ui.setting.component.CustomSwitch
import org.sopt.certi.presentation.ui.setting.component.MarketingInfoBox
import org.sopt.certi.presentation.ui.setting.state.SettingUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun SettingNotificationRoute(
    padding: PaddingValues,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isDialogVisible) {
        CertiDialog(
            text = stringResource(R.string.setting_dialog_message),
            onConfirmClick = viewModel::onDialogConfirm,
            onDismissClick = viewModel::onDialogDismiss
        )
    }

    SettingNotificationScreen(
        uiState = uiState,
        onSwitchCheckChange = viewModel::onSwitchCheckChange,
        onCheckboxCheckChange = viewModel::onCheckboxCheckChange,
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun SettingNotificationScreen(
    uiState: SettingUiState,
    onSwitchCheckChange: (Boolean) -> Unit,
    onCheckboxCheckChange: (Boolean) -> Unit,
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
            MarketingInfoBox()
            Spacer(modifier = Modifier.weight(1f))
            CustomSwitch(
                checked = uiState.switchChecked,
                onCheckedChange = onSwitchCheckChange
            )
        }
        Spacer(modifier = Modifier.heightForScreenPercentage(12.dp))

        Text(
            text = stringResource(R.string.setting_marketing_description),
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
        )

        Column(
            modifier = Modifier
                .padding(top = screenHeightDp(24.dp))
                .background(CertiTheme.colors.gray0)
                .padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(16.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(12.dp))
        ) {
            Row {
                CustomCheckbox(
                    checked = uiState.checkboxChecked,
                    onCheckedChange = onCheckboxCheckChange
                )
                Spacer(modifier = Modifier.widthForScreenPercentage(12.dp))

                Text(
                    text = stringResource(R.string.setting_select),
                    style = CertiTheme.typography.body.regular_16,
                    color = CertiTheme.colors.gray400
                )
                Spacer(modifier = Modifier.widthForScreenPercentage(4.dp))
                Text(
                    text = stringResource(R.string.setting_privacy_consent),
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.black
                )

                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_24),
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(R.string.setting_privacy_description),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray400
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingNotificationPreview() {
    CERTITheme {
        var uiState by remember {
            mutableStateOf(
                SettingUiState(
                    switchChecked = false,
                    checkboxChecked = false
                )
            )
        }
        SettingNotificationScreen(
            uiState = uiState,
            onSwitchCheckChange = { },
            onCheckboxCheckChange = {}
        )
    }
}

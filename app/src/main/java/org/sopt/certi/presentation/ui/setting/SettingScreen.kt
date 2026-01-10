package org.sopt.certi.presentation.ui.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.topbar.MyPageTopBar
import org.sopt.certi.core.component.listitem.MenuRow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.setting.component.LogoutButton
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.sopt.certi.core.component.dialog.CertiDeleteDialog
import org.sopt.certi.core.util.appVersion
import org.sopt.certi.presentation.ui.setting.component.DeleteAccountDialog

@Composable
fun SettingRoute(
    padding: PaddingValues,
    navigateToSettingNotification: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLogoutDialogVisible) {
        CertiDeleteDialog(
            onConfirmClick = viewModel::onLogoutDialogConfirm,
            onDismissClick = viewModel::onLogoutDialogDismiss,
            title = stringResource(R.string.setting_logout_dialog_message),
            description = stringResource(R.string.setting_logout_dialog_description)
        )
    }

    if (uiState.isDeleteAccountDialogVisible) {
        DeleteAccountDialog(
            onConfirmClick = viewModel::onDeleteAccountDialogConfirm,
            onDismissClick = viewModel::onDeleteAccountDialogDismiss
        )
    }

    SettingScreen(
        onNavigateToSettingNotification = navigateToSettingNotification,
        onDeleteAccountClick = viewModel::onDeleteAccountClick,
        onLogoutClick = viewModel::onLogoutClick,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun SettingScreen(
    onNavigateToSettingNotification: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val appVersion = remember { context.appVersion }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        MyPageTopBar(
            headerTitle = stringResource(R.string.mypage_setting),
            modifier = Modifier.padding(vertical = screenHeightDp(24.dp))
        )
        Spacer(modifier = Modifier.heightForScreenPercentage(12.dp))

        Text(
            text = stringResource(R.string.setting_other),
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400
        )
        Spacer(modifier = Modifier.heightForScreenPercentage(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(36.dp))
        ) {
            MenuRow(
                text = stringResource(R.string.settings_notification),
                onClick = onNavigateToSettingNotification
            )

            MenuRow(
                text = stringResource(R.string.setting_privacy_policy),
                onClick = {}
            )

            MenuRow(
                text = stringResource(R.string.setting_delete_account),
                onClick = onDeleteAccountClick
            )

            MenuRow(
                text = stringResource(R.string.setting_app_version),
                trailingContent = {
                    Text(
                        text = appVersion,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.gray500
                    )
                }
            )
        }

        LogoutButton(
            modifier = Modifier.padding(vertical = screenHeightDp(36.dp)),
            onClick = onLogoutClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingPreview() {
    CERTITheme {
        SettingScreen(
            onNavigateToSettingNotification = {},
            onDeleteAccountClick = {},
            onLogoutClick = {}
        )
    }
}

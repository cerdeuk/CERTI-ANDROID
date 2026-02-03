package org.sopt.certi.presentation.ui.setting.state

import org.sopt.certi.core.state.UiState

data class SettingNotificationUiState(
    val agreementLoadState: UiState<Unit> = UiState.Loading,
    val switchChecked: Boolean = false,
    val checkboxChecked: Boolean = false,
    val isMarketingConfirmDialogVisible: Boolean = false
)

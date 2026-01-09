package org.sopt.certi.presentation.ui.setting.state

data class SettingUiState(
    val switchChecked: Boolean = false,
    val checkboxChecked: Boolean = false,
    val isMarketingConfirmDialogVisible: Boolean = false,
    val isLogoutDialogVisible: Boolean = false
)

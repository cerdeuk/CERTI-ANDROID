package org.sopt.certi.presentation.ui.setting.sideEffect

sealed interface SettingNotificationSideEffect {
    data object ShowMarketingConfirmSnackbar : SettingNotificationSideEffect
}

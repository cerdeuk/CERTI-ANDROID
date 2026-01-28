package org.sopt.certi.presentation.ui.setting.sideEffect

sealed interface SettingSideEffect {
    data object ShowMarketingConfirmSnackbar : SettingSideEffect
    data object NavigateToLogin : SettingSideEffect
}

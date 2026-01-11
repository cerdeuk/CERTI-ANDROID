package org.sopt.certi.presentation.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.presentation.ui.setting.sideEffect.SettingSideEffect
import org.sopt.certi.presentation.ui.setting.state.SettingUiState
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SettingSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onSwitchCheckChange(checked: Boolean) {
        if (checked) {
            _uiState.update { it.copy(isMarketingConfirmDialogVisible = true) }
        } else {
            _uiState.update { it.copy(switchChecked = false) }
        }
    }

    fun onMarketingConfirmDialogConfirm() {
        _uiState.update {
            it.copy(
                switchChecked = true,
                checkboxChecked = true,
                isMarketingConfirmDialogVisible = false
            )
        }
        showMarketingSnackbar()
    }

    fun onMarketingConfirmDialogDismiss() {
        _uiState.update { it.copy(isMarketingConfirmDialogVisible = false) }
    }

    fun onCheckboxCheckChange(checked: Boolean) {
        _uiState.update { it.copy(checkboxChecked = checked) }

        if (checked) {
            showMarketingSnackbar()
        }
    }

    private fun showMarketingSnackbar() {
        viewModelScope.launch {
            _sideEffect.send(SettingSideEffect.ShowMarketingConfirmSnackbar)
        }
    }

    fun onLogoutClick() {
        _uiState.update { it.copy(isLogoutDialogVisible = true) }
    }

    fun onLogoutDialogConfirm() {
        _uiState.update { it.copy(isLogoutDialogVisible = false) }
    }

    fun onLogoutDialogDismiss() {
        _uiState.update { it.copy(isLogoutDialogVisible = false) }
    }

    fun onDeleteAccountClick() {
        _uiState.update { it.copy(isDeleteAccountDialogVisible = true) }
    }

    fun onDeleteAccountDialogConfirm() {
        _uiState.update { it.copy(isDeleteAccountDialogVisible = false) }
    }

    fun onDeleteAccountDialogDismiss() {
        _uiState.update { it.copy(isDeleteAccountDialogVisible = false) }
    }
}

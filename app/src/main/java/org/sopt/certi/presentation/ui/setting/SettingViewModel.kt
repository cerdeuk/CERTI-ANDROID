package org.sopt.certi.presentation.ui.setting

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.certi.presentation.ui.setting.state.SettingUiState
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()

    fun onSwitchCheckChange(checked: Boolean) {
        if (checked) {
            _uiState.update { it.copy(isDialogVisible = true) }
        } else {
            _uiState.update { it.copy(switchChecked = false) }
        }
    }

    fun onDialogConfirm() {
        _uiState.update {
            it.copy(
                switchChecked = true,
                checkboxChecked = true,
                isDialogVisible = false
            )
        }
    }

    fun onDialogDismiss() {
        _uiState.update { it.copy(isDialogVisible = false) }
    }

    fun onCheckboxCheckChange(checked: Boolean) {
        _uiState.update { it.copy(checkboxChecked = checked) }
    }
}

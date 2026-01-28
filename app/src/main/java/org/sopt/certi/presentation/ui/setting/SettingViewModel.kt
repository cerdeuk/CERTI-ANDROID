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
import org.sopt.certi.domain.usecase.user.GetMarketingAgreementUseCase
import org.sopt.certi.domain.usecase.user.PatchMarketingAgreementUseCase
import org.sopt.certi.presentation.ui.setting.sideEffect.SettingSideEffect
import org.sopt.certi.presentation.ui.setting.state.SettingUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getMarketingAgreementUseCase: GetMarketingAgreementUseCase,
    private val patchMarketingAgreementUseCase: PatchMarketingAgreementUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SettingSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        getMarketingAgreement()
    }

    private fun getMarketingAgreement() = viewModelScope.launch {
        getMarketingAgreementUseCase()
            .onSuccess { result ->
                if (result) {
                    _uiState.update {
                        it.copy(
                            switchChecked = true,
                            checkboxChecked = true
                        )
                    }
                }
            }
            .onFailure { error ->
                Timber.d(error, "광고성 정보 수신 동의 정보 불러오기 실패")
            }
    }

    fun onSwitchCheckChange(checked: Boolean) = viewModelScope.launch {
        if (checked) {
            _uiState.update { it.copy(isMarketingConfirmDialogVisible = true) }
        } else {
            patchMarketingAgreementUseCase()
                .onSuccess {
                    _uiState.update { it.copy(switchChecked = false) }
                }
                .onFailure { error ->
                    Timber.e(error, "광고성 정보 수신 동의 체크 해제 실패")
                }
        }
    }

    fun onMarketingConfirmDialogConfirm() = viewModelScope.launch {
        patchMarketingAgreementUseCase()
            .onSuccess {
                _uiState.update {
                    it.copy(
                        switchChecked = true,
                        checkboxChecked = true,
                        isMarketingConfirmDialogVisible = false
                    )
                }
                showMarketingSnackbar()
            }
            .onFailure { error ->
                Timber.e(error, "광고성 수신 정보 동의 실패")
            }
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

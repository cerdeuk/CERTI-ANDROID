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
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.usecase.user.GetMarketingPrivacyUseCase
import org.sopt.certi.domain.usecase.user.PatchMarketingAgreementUseCase
import org.sopt.certi.domain.usecase.user.PatchPrivacyAgreementUseCase
import org.sopt.certi.presentation.ui.setting.sideEffect.SettingNotificationSideEffect
import org.sopt.certi.presentation.ui.setting.state.SettingNotificationUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingNotificationViewModel @Inject constructor(
    private val getMarketingPrivacyUseCase: GetMarketingPrivacyUseCase,
    private val patchMarketingAgreementUseCase: PatchMarketingAgreementUseCase,
    private val patchPrivacyAgreementUseCase: PatchPrivacyAgreementUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingNotificationUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SettingNotificationSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        getMarketingPrivacyAgreement()
    }

    private fun getMarketingPrivacyAgreement() = viewModelScope.launch {
        getMarketingPrivacyUseCase()
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        agreementLoadState = UiState.Success(Unit),
                        switchChecked = result.isAdvertisingAgreement && result.isPrivacyAgreement,
                        checkboxChecked = result.isPrivacyAgreement
                    )
                }
            }
            .onFailure { error ->
                Timber.d(error, "광고성 정보 수신 동의 정보 불러오기 실패")
            }
    }

    fun onSwitchCheckChange(checked: Boolean) {
        if (_uiState.value.checkboxChecked) {
            viewModelScope.launch {
                patchMarketingAgreementUseCase(checked)
                    .onSuccess {
                        _uiState.update { it.copy(switchChecked = checked) }
                    }
                    .onFailure { error ->
                        Timber.e(error, "광고성 정보 수신 동의 업데이트 실패")
                    }
            }
        } else {
            _uiState.update { it.copy(isMarketingConfirmDialogVisible = true) }
        }
    }

    fun onMarketingConfirmDialogConfirm() = viewModelScope.launch {
        patchPrivacyAgreementUseCase(true)
            .onSuccess {
                patchMarketingAgreementUseCase(true)
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
                        Timber.e(error, "광고성 정보 수신 동의 실패")
                    }
            }
            .onFailure { error ->
                Timber.e(error, "개인정보 수집 및 이용 동의 실패")
            }
    }

    fun onMarketingConfirmDialogDismiss() {
        _uiState.update { it.copy(isMarketingConfirmDialogVisible = false) }
    }

    fun onCheckboxCheckChange(checked: Boolean) = viewModelScope.launch {
        patchPrivacyAgreementUseCase(checked)
            .onSuccess {
                _uiState.update { state ->
                    state.copy(
                        checkboxChecked = checked,
                        switchChecked = if (!checked) false else state.switchChecked
                    )
                }
                if (checked) showMarketingSnackbar()
            }
            .onFailure { error ->
                Timber.e(error, "개인정보 동의 업데이트 실패")
            }
    }

    private fun showMarketingSnackbar() {
        viewModelScope.launch {
            _sideEffect.send(SettingNotificationSideEffect.ShowMarketingConfirmSnackbar)
        }
    }
}

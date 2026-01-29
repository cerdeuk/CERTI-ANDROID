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
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.domain.usecase.auth.WithDrawUseCase
import org.sopt.certi.presentation.ui.setting.sideEffect.SettingSideEffect
import org.sopt.certi.presentation.ui.setting.state.SettingUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val withDrawUseCase: WithDrawUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SettingSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onLogoutClick() {
        _uiState.update { it.copy(isLogoutDialogVisible = true) }
    }

    fun onLogoutDialogConfirm() {
        tokenManager.clear()
        _uiState.update { it.copy(isLogoutDialogVisible = false) }
        viewModelScope.launch {
            _sideEffect.send(SettingSideEffect.NavigateToLogin)
        }
    }

    fun onLogoutDialogDismiss() {
        _uiState.update { it.copy(isLogoutDialogVisible = false) }
    }

    fun onDeleteAccountClick() {
        _uiState.update { it.copy(isDeleteAccountDialogVisible = true) }
    }

    fun onDeleteAccountDialogConfirm() = viewModelScope.launch {
        withDrawUseCase()
            .onSuccess {
                tokenManager.clear()
                _uiState.update { it.copy(isDeleteAccountDialogVisible = false) }
                _sideEffect.send(SettingSideEffect.NavigateToLogin)
            }
            .onFailure { error ->
                Timber.e(error, "회원탈퇴 실패")
            }
    }

    fun onDeleteAccountDialogDismiss() {
        _uiState.update { it.copy(isDeleteAccountDialogVisible = false) }
    }
}

package org.sopt.certi.presentation.ui.certdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.acquisition.AcquiredCertUseCase
import org.sopt.certi.domain.usecase.certification.GetCertInfoUseCase
import org.sopt.certi.domain.usecase.precert.AcquireExpectCertUseCase
import org.sopt.certi.presentation.ui.certdetail.sideeffect.DetailSideEffect
import org.sopt.certi.presentation.ui.certdetail.state.DetailUiState
import javax.inject.Inject

@HiltViewModel
class CertDetailViewModel @Inject constructor(
    private val getCertInfoUseCase: GetCertInfoUseCase,
    private val acquiredCertUseCase: AcquiredCertUseCase,
    private val acquireExpectCertUseCase: AcquireExpectCertUseCase
) : ViewModel() {

    private val _certDetailInfo = MutableStateFlow<UiState<CertificationData>>(UiState.Init)

    val detailUiState: StateFlow<DetailUiState> =
        combine(
            _certDetailInfo
        ) { loadState ->
            DetailUiState(
                detailCertificationLoadState = loadState[0]
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState(
                detailCertificationLoadState = UiState.Init
            )
        )

    private val _sideEffect = Channel<DetailSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getCertDetailInfo(certId: Long) = viewModelScope.launch {
        getCertInfoUseCase.invoke(certId).fold(
            onSuccess = {
                _certDetailInfo.emit(UiState.Success(it))
            },
            onFailure = {
                _certDetailInfo.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun acquireExpectCert(certId: Long) = viewModelScope.launch {
        acquireExpectCertUseCase.invoke(certId).fold(
            onSuccess = {
                if (it) {
                    _sideEffect.send(DetailSideEffect.ShowAcquireExpectSuccessToast)
                } else {
                    _sideEffect.send(DetailSideEffect.ShowAcquireExpectFailToast)
                }
            },
            onFailure = {
                if (it.message?.contains("Conflict") == true) {
                    _sideEffect.send(DetailSideEffect.ShowAcquiredFailToast)
                }
            }
        )
    }

    fun acquiredCert(certId: Long) = viewModelScope.launch {
        acquiredCertUseCase.invoke(certId).fold(
            onSuccess = {
                if (it) {
                    _sideEffect.send(DetailSideEffect.ShowAcquiredSuccessDialog)
                } else {
                    _sideEffect.send(DetailSideEffect.ShowAcquiredFailToast)
                }
            },
            onFailure = {}
        )
    }

    fun getCommentList() = viewModelScope.launch {
    }
}

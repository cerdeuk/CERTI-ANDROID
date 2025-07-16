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
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.certification.GetCertInfoUseCase
import org.sopt.certi.presentation.ui.certdetail.sideeffect.DetailSideEffect
import org.sopt.certi.presentation.ui.certdetail.state.DetailUiState
import javax.inject.Inject

@HiltViewModel
class CertDetailViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase,
    private val getCertInfoUseCase: GetCertInfoUseCase
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
        // TODO 취득 예정 로직

        _sideEffect.send(DetailSideEffect.ShowAcquireExpectSuccessToast)
        _sideEffect.send(DetailSideEffect.ShowAcquireExpectFailToast)
    }

    fun acquiredCert(certId: Long) = viewModelScope.launch {
        // TODO 취득 완료 로직

        _sideEffect.send(DetailSideEffect.ShowAcquiredSuccessDialog)
        _sideEffect.send(DetailSideEffect.ShowAcquiredFailToast)
    }
}

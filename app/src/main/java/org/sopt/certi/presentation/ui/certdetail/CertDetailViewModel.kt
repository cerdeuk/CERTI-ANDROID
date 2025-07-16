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
import org.sopt.certi.presentation.ui.certdetail.sideeffect.DetailSideEffect
import org.sopt.certi.presentation.ui.certdetail.state.DetailUiState
import javax.inject.Inject

@HiltViewModel
class CertDetailViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
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
        // TODO 자격증 상세정보 데이터 받아오는 로직

        val dummyCertInfo = CertificationData(
            certificationId = 0,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            averagePeriod = "100일",
            agencyName = "국가기술자격",
            testType = "실기형",
            tags = listOf("aa", "bb", "cc"),
            charge = 12000,
            description = "2D 그래픽 툴의 기능을 활용한 사고의 시각화를 통해 이미지 제작, 수정, 편집 및 그래픽 디자인을 창출하는 업무를 수행하고 이를 통해 비지니스 커뮤니케이션을 원활하게 한다. 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이이다.",
            testDateInformation = "매월 넷째주 토요일 정기시험 시행 (총 12회)",
            applicationMethod = "온라인(한국생산성본부 홈페이지)",
            applicationUrl = "www.google.com",
            expirationPeriod = "1년"
        )

        _certDetailInfo.emit(UiState.Success(dummyCertInfo))
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

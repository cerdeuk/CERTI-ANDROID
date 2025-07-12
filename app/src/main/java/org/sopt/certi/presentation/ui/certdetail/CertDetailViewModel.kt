package org.sopt.certi.presentation.ui.certdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import javax.inject.Inject

@HiltViewModel
class CertDetailViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {

    private val _certDetailInfo = MutableStateFlow<UiState<CertificationData?>>(UiState.Init)
    val certDetailInfo = _certDetailInfo.asStateFlow()

    fun getCertDetailInfo(certId: Long) = viewModelScope.launch {
        // TODO 서버 데이터 받아오는 로직

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
}

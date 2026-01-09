package org.sopt.certi.presentation.ui.certlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.HomeRecommendUseCase
import org.sopt.certi.domain.usecase.certification.Top3TrackCertListUseCase
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import javax.inject.Inject

@HiltViewModel
class CertListViewModel @Inject constructor(
    private val homeRecommendUseCase: HomeRecommendUseCase,
    private val top3TrackCertListUseCase: Top3TrackCertListUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    val nickname: StateFlow<String> =
        tokenManager.nicknameFlow()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = tokenManager.getNickName()
            )
    private val _recommendCertListLoadState = MutableStateFlow<UiState<ImmutableList<CertificationData>>>(UiState.Loading)
    private val _trackTop3CertListLoadState = MutableStateFlow<UiState<ImmutableList<CertificationData>>>(UiState.Loading)
    private val _categoryTop3CertListLoadState = MutableStateFlow<UiState<ImmutableList<CertificationData>>>(UiState.Loading)

    val certificationListUiState: StateFlow<CertListUiState> =
        combine(_recommendCertListLoadState, _trackTop3CertListLoadState, _categoryTop3CertListLoadState) { recommendCertListLoadState, trackTop3CertListLoadState, categoryTop3CertListLoadState ->
            CertListUiState(
                recommendListLoadState = recommendCertListLoadState,
                trackTop3ListLoadState = trackTop3CertListLoadState,
                categoryTop3ListLoadState = categoryTop3CertListLoadState
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CertListUiState(
                recommendListLoadState = UiState.Loading,
                trackTop3ListLoadState = UiState.Loading,
                categoryTop3ListLoadState = UiState.Loading
            )
        )

    init {
        getRecommendCertificationList()
        getTrackTop3CertificationList()
        getCategoryTop3CertificationList()
    }

    private fun getRecommendCertificationList() = viewModelScope.launch {
        _recommendCertListLoadState.value = UiState.Loading
        homeRecommendUseCase()
            .onSuccess { result ->
                _recommendCertListLoadState.value = UiState.Success(result.toImmutableList())
            }
            .onFailure {
                _recommendCertListLoadState.value = UiState.Failure(it.toString())
            }
    }

    private fun getTrackTop3CertificationList() = viewModelScope.launch {
        _trackTop3CertListLoadState.value = UiState.Loading
        top3TrackCertListUseCase()
            .onSuccess {
                _trackTop3CertListLoadState.value = UiState.Success(it.toImmutableList())
            }
            .onFailure {
                _trackTop3CertListLoadState.value = UiState.Failure(it.toString())
            }
    }

    private fun getCategoryTop3CertificationList() = viewModelScope.launch {
        _categoryTop3CertListLoadState.value = UiState.Loading
        // 직무 카테고리 서버통신 로직
        _categoryTop3CertListLoadState.value = UiState.Success(
            listOf(
                CertificationData(
                    certificationId = 1,
                    certificationName = "정보처리기사",
                    tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                    isFavorite = true,
                    testType = "실기형",
                    recommendScore = 20,
                    agencyName = "국가기술자격",
                    description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                ),
                CertificationData(
                    certificationId = 2,
                    certificationName = "GTQ 1급 (그래픽 기술 자격)",
                    tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                    isFavorite = false,
                    testType = "실기형",
                    recommendScore = 90,
                    agencyName = "국가기술자격",
                    description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                ),
                CertificationData(
                    certificationId = 3,
                    certificationName = "TOEIC 900+",
                    tags = listOf("경영", "시각디자인", "컴퓨터공학"),
                    isFavorite = true,
                    testType = "실기형",
                    recommendScore = 80,
                    agencyName = "국가기술자격",
                    description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                )
            ).toImmutableList()
        )
    }
}

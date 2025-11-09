package org.sopt.certi.presentation.ui.trackcategorycertlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.domain.type.TrackType
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.certification.GetCategoryCertListUseCase
import org.sopt.certi.presentation.ui.trackcategorycertlist.state.TrackCategoryCertListUiState
import org.sopt.certi.presentation.ui.trackcategorycertlist.model.TrackCategoryType
import javax.inject.Inject

@HiltViewModel
class TrackCategoryCertListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCategoryCertListUseCase: GetCategoryCertListUseCase,
//    private val getTrackCertListUseCase: GetTrackCertListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    val mode: TrackCategoryType =
        TrackCategoryType.fromRouteArg(savedStateHandle.get<String>("mode"))

    private val _certListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _selectedCategory = MutableStateFlow(0)
    private val _isFavorite = MutableStateFlow(false)

    val certificationListUiState: StateFlow<TrackCategoryCertListUiState> =
        combine(_certListLoadState, _selectedCategory, _isFavorite) { certListLoadState, selectedCategory, isFavorite ->
            TrackCategoryCertListUiState(
                certificationListLoadState = certListLoadState,
                selectedCategory = selectedCategory,
                isFavorite = isFavorite
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrackCategoryCertListUiState(
                certificationListLoadState = UiState.Loading,
                selectedCategory = 0,
                isFavorite = false
            )
        )

    fun getCertificationList(
        isFavorite: Boolean,
        category: Int
    ) {
        viewModelScope.launch {
            _certListLoadState.value = UiState.Loading
            when (mode) {
                TrackCategoryType.CATEGORY -> getCategoryCertificationList(isFavorite, category)
                TrackCategoryType.TRACK -> getTrackCertificationList(isFavorite, category)
            }
        }
    }

    private fun getCategoryCertificationList(
        isFavorite: Boolean,
        category: Int
    ) = viewModelScope.launch {
        _certListLoadState.value = UiState.Loading
        val jobs = CategoryType.entries.getOrNull(category)?.description ?: ""
//        getCategoryCertListUseCase(isFavorite, jobs)
//            .onSuccess { list -> _certListLoadState.value = if (list.isEmpty()) UiState.Empty else UiState.Success(list) }
//            .onFailure { _certListLoadState.value = UiState.Failure(it.toString()) }

        _certListLoadState.value = UiState.Success(
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

    private fun getTrackCertificationList(
        isFavorite: Boolean,
        track: Int
    ) = viewModelScope.launch {
        _certListLoadState.value = UiState.Loading
        val tracks = TrackType.entries.getOrNull(track)?.description ?: ""
//        getTrackCertListUseCase(isFavorite, tracks)
//            .onSuccess { list -> _certListLoadState.value = if (list.isEmpty()) UiState.Empty else UiState.Success(list) }
//            .onFailure { _certListLoadState.value = UiState.Failure(it.toString()) }
        _certListLoadState.value = UiState.Success(
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

    fun onCategorySelected(index: Int) {
        _selectedCategory.value = index
    }

    fun onFavoriteClick() {
        _isFavorite.value = !_isFavorite.value
    }

    fun onLikeClick(certificationId: Long) {
        viewModelScope.launch {
            toggleFavoriteUseCase(certificationId)
                .onSuccess {
                    val currentState = _certListLoadState.value
                    if (currentState is UiState.Success) {
                        val updatedList = currentState.data.map {
                            if (it.certificationId == certificationId) {
                                it.copy(isFavorite = !it.isFavorite)
                            } else {
                                it
                            }
                        }
                        _certListLoadState.value = UiState.Success(updatedList)
                    }
                }
                .onFailure {
                    _certListLoadState.value = UiState.Failure(it.toString())
                }
        }
    }
}

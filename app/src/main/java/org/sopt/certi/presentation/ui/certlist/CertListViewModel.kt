package org.sopt.certi.presentation.ui.certlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.HomeRecommendUseCase
import org.sopt.certi.domain.usecase.certification.Top3JobCertListUseCase
import org.sopt.certi.domain.usecase.certification.Top3TrackCertListUseCase
import org.sopt.certi.domain.usecase.user.GetInterestedJobListUseCase
import org.sopt.certi.domain.usecase.user.UserTrackUseCase
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import javax.inject.Inject

@HiltViewModel
class CertListViewModel @Inject constructor(
    private val homeRecommendUseCase: HomeRecommendUseCase,
    private val top3TrackCertListUseCase: Top3TrackCertListUseCase,
    private val top3JobCertListUseCase: Top3JobCertListUseCase,
    private val getInterestedJobListUseCase: GetInterestedJobListUseCase,
    private val userTrackUseCase: UserTrackUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    val nickname: StateFlow<String> =
        tokenManager.nicknameFlow()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = tokenManager.getNickName()
            )

    private val _job = MutableStateFlow("")
    val job = _job.asStateFlow()

    private val _track = MutableStateFlow("")
    val track = _track.asStateFlow()

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
        getUserTrack()
        getTrackTop3CertificationList()
        getUserJob()
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

    private fun getUserTrack() = viewModelScope.launch {
        userTrackUseCase()
            .onSuccess {
                _track.value = it
            }
            .onFailure {
                _track.value = ""
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

    private fun getUserJob() = viewModelScope.launch {
        getInterestedJobListUseCase()
            .onSuccess {
                _job.value = it.jobList.first()
            }
            .onFailure {
                _job.value = ""
            }
    }

    private fun getCategoryTop3CertificationList() = viewModelScope.launch {
        _categoryTop3CertListLoadState.value = UiState.Loading
        top3JobCertListUseCase()
            .onSuccess {
                _categoryTop3CertListLoadState.value = UiState.Success(it.toImmutableList())
            }
            .onFailure {
                _categoryTop3CertListLoadState.value = UiState.Failure(it.toString())
            }
    }
}

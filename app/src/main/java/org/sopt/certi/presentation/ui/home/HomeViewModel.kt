package org.sopt.certi.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import org.sopt.certi.core.state.UiState
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import javax.inject.Inject
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.FavoriteUseCase
import org.sopt.certi.domain.usecase.PreCertUseCase
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.presentation.ui.home.state.HomeUiState
import kotlin.onSuccess

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val preCertUseCase: PreCertUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _userInfoLoadState = MutableStateFlow<UiState<UserInfoData>>(UiState.Loading)
    private val _recommendedListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _preCertificationListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _favoriteListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)

    val homeUiState: StateFlow<HomeUiState> = combine(
        _userInfoLoadState,
        _recommendedListLoadState,
        _preCertificationListLoadState,
        _favoriteListLoadState
    ) { userInfo, recommended, preCerti, favorite ->
        HomeUiState(
            userInfoLoadState = userInfo,
            recommendedListLoadState = recommended,
            preCertificationListLoadState = preCerti,
            favoriteListLoadState = favorite
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HomeUiState(
            userInfoLoadState = UiState.Loading,
            recommendedListLoadState = UiState.Loading,
            preCertificationListLoadState = UiState.Loading,
            favoriteListLoadState = UiState.Loading,
            isFavorite = true
        )
    )

    fun getUserInfo() {
        viewModelScope.launch {
            _userInfoLoadState.value = UiState.Loading
            userInfoUseCase()
                .onSuccess { result ->
                    _userInfoLoadState.value = UiState.Success(result)
                }
                .onFailure {
                    _userInfoLoadState.value = UiState.Failure(it.toString())
                }
        }
    }


    fun getRecommendedList() {
        val recommendedList = {
            listOf<CertificationData>(
                CertificationData(
                    certificationId = 1,
                    certificationName = "OPIc",
                    recommendScore = 90,
                    tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                ),
                CertificationData(
                    certificationId = 2,
                    certificationName = "시각디자인산업기사",
                    recommendScore = 90,
                    tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                ),
                CertificationData(
                    certificationId = 3,
                    certificationName = "정보처리기사",
                    recommendScore = 90,
                    tags = listOf("컴퓨터공학", "재무/세무/IR", "재무/세무/IR")
                )
            )
        }

        _recommendedListLoadState.value = UiState.Success(recommendedList())
    }

    fun getPreCertList() {
        viewModelScope.launch {
            _preCertificationListLoadState.value = UiState.Loading
            preCertUseCase()
                .onSuccess { result ->
                    _preCertificationListLoadState.value = UiState.Success(result)
                }
                .onFailure {
                    _preCertificationListLoadState.value = UiState.Failure(it.toString())
                }
        }

    }

    fun getFavoriteList(isFavorite: Boolean) {
        viewModelScope.launch {
            _favoriteListLoadState.value = UiState.Loading
            favoriteUseCase()
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _favoriteListLoadState.value = UiState.Empty
                    } else {
                        _favoriteListLoadState.value = UiState.Success(result)
                    }
                }.onFailure {
                    _favoriteListLoadState.value = UiState.Failure(it.toString())
                }
        }
    }

    fun onFavoriteClicked(certificationId: Long) {
        viewModelScope.launch {
            toggleFavoriteUseCase(certificationId)
            val current = _favoriteListLoadState.value
            if (current is UiState.Success) {
                val updated = current.data.map {
                    if (it.certificationId == certificationId) {
                        it.copy(isFavorite = !it.isFavorite)
                    } else it
                }
                _favoriteListLoadState.value = UiState.Success(updated)
            }
        }
    }

}


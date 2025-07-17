package org.sopt.certi.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.usecase.FavoriteUseCase
import org.sopt.certi.domain.usecase.HomeRecommendUseCase
import org.sopt.certi.domain.usecase.PreCertUseCase
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.domain.usecase.WithDrawUseCase
import org.sopt.certi.presentation.ui.home.state.HomeUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val homeRecommendUseCase: HomeRecommendUseCase,
    private val preCertUseCase: PreCertUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val withDrawUseCase: WithDrawUseCase,
    private val tokenManager: TokenManager
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
            favoriteListLoadState = UiState.Loading
        )
    )

    fun getUserInfo() {
        viewModelScope.launch {
            _userInfoLoadState.value = UiState.Loading
            userInfoUseCase()
                .onSuccess { result ->
                    _userInfoLoadState.value = UiState.Success(result)
                    tokenManager.saveNickName(result.name)
                }
                .onFailure {
                    _userInfoLoadState.value = UiState.Failure(it.toString())
                }
        }
    }

    fun getRecommendedList() {
        viewModelScope.launch {
            _recommendedListLoadState.value = UiState.Loading
            homeRecommendUseCase()
                .onSuccess { result ->
                    _recommendedListLoadState.value = UiState.Success(result)
                }
                .onFailure {
                    _recommendedListLoadState.value = UiState.Failure(it.toString())
                }
        }
    }

    fun getPreCertList() {
        viewModelScope.launch {
            _preCertificationListLoadState.value = UiState.Loading
            preCertUseCase()
                .onSuccess { result ->
                    _preCertificationListLoadState.value = UiState.Success(result.take(3))
                }
                .onFailure {
                    _preCertificationListLoadState.value = UiState.Failure(it.toString())
                }
        }
    }

    fun getFavoriteList() {
        viewModelScope.launch {
            _favoriteListLoadState.value = UiState.Loading
            favoriteUseCase()
                .onSuccess { result ->
                    _favoriteListLoadState.value = UiState.Success(result)
                }.onFailure { e ->
                    _favoriteListLoadState.value = UiState.Failure(e.toString())
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
                    } else {
                        it
                    }
                }
                _favoriteListLoadState.value = UiState.Success(updated)
            }
        }
    }

    fun withDraw() = viewModelScope.launch {
        withDrawUseCase.invoke()
    }

    fun clearSharedPreference() {
        tokenManager.clear()
    }
}

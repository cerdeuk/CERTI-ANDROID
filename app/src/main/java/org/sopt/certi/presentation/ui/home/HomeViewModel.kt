package org.sopt.certi.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.PreCertDayData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.usecase.FavoriteUseCase
import org.sopt.certi.domain.usecase.HomeRecommendUseCase
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.domain.usecase.auth.WithDrawUseCase
import org.sopt.certi.domain.usecase.certification.GetPreCertDayUseCase
import org.sopt.certi.domain.usecase.certification.GetPreCertMonthUseCase
import org.sopt.certi.presentation.ui.home.state.HomeUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val homeRecommendUseCase: HomeRecommendUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val withDrawUseCase: WithDrawUseCase,
    private val getPreCertMonthUseCase: GetPreCertMonthUseCase,
    private val getPreCertDayUseCase: GetPreCertDayUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _userInfoLoadState = MutableStateFlow<UiState<UserInfoData>>(UiState.Loading)
    private val _recommendedListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _favoriteListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _preCertMonthData = MutableStateFlow<UiState<List<Int>>>(UiState.Loading)
    private val _preCertDayData = MutableStateFlow<UiState<PreCertDayData>>(UiState.Loading)

    val homeUiState: StateFlow<HomeUiState> = combine(
        _userInfoLoadState,
        _recommendedListLoadState,
        _favoriteListLoadState,
        _preCertMonthData,
        _preCertDayData
    ) { userInfo, recommended, favorite, preCertMonth, preCertDay ->
        HomeUiState(
            userInfoLoadState = userInfo,
            recommendedListLoadState = recommended,
            favoriteListLoadState = favorite,
            preCertMonthLoadState = preCertMonth,
            preCertDayLoadState = preCertDay
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HomeUiState(
            userInfoLoadState = UiState.Loading,
            recommendedListLoadState = UiState.Loading,
            favoriteListLoadState = UiState.Loading,
            preCertMonthLoadState = UiState.Loading,
            preCertDayLoadState = UiState.Loading
        )
    )

    fun getUserInfo() {
        viewModelScope.launch {
            _userInfoLoadState.value = UiState.Loading
            userInfoUseCase()
                .onSuccess { result ->
                    _userInfoLoadState.value = UiState.Success(result)
                    tokenManager.saveNickName(result.nickname)
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

    fun getPreCertMonth(year: Int, month: Int) = viewModelScope.launch {
        getPreCertMonthUseCase.invoke(year, month)
            .onSuccess {
                _preCertMonthData.value = UiState.Success(it)
            }
            .onFailure {
                _preCertMonthData.value = UiState.Failure(it.toString())
            }
    }

    fun getPreCertDay(date: String) = viewModelScope.launch {
        getPreCertDayUseCase.invoke(date)
            .onSuccess {
                _preCertDayData.value = UiState.Success(it)
            }
            .onFailure {
                _preCertDayData.value = UiState.Failure(it.toString())
            }
    }

    fun withDraw() = viewModelScope.launch {
        withDrawUseCase.invoke()
    }

    fun clearSharedPreference() {
        tokenManager.clear()
    }
}

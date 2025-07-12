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
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.model.UserInfoData
import javax.inject.Inject
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.home.state.HomeUiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _userInfoLoadState = MutableStateFlow<UiState<UserInfoData>>(UiState.Loading)
    val userInfoLoadState: StateFlow<UiState<UserInfoData>> = _userInfoLoadState

    private val _recommendedListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    val recommendedListLoadState: StateFlow<UiState<List<CertificationData>>> = _recommendedListLoadState

    private val _preCertificationListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    val preCertListLoadState: StateFlow<UiState<List<CertificationData>>> = _preCertificationListLoadState

    private val _favoriteListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    val favoriteListLoadState: StateFlow<UiState<List<CertificationData>>> = _favoriteListLoadState

    private val _isFavorite = MutableStateFlow(true)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    val homeUiState: StateFlow<HomeUiState> = combine(
        _userInfoLoadState,
        _recommendedListLoadState,
        _preCertificationListLoadState,
        _favoriteListLoadState,
        _isFavorite
    ) { userInfo, recommended, preCerti, favorite, isFavorite ->
        HomeUiState(
            userInfoLoadState = userInfo,
            recommendedListLoadState = recommended,
            preCertificationListLoadState = preCerti,
            favoriteListLoadState = favorite,
            isFavorite = isFavorite

    )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(
            userInfoLoadState = UiState.Loading,
            recommendedListLoadState = UiState.Loading,
            preCertificationListLoadState = UiState.Loading,
            favoriteListLoadState = UiState.Loading,
            isFavorite = true
        )
    )

    init {
        getuserInfo()
        getRecommendedList()
        getPreCertList()
        getFavoriteList(isFavorite = true)
    }

    fun getuserInfo() {
        val userInfo = UserInfoData(
            name = "",
            university = "",
            major = "",
            percentage = 0
        )
        _userInfoLoadState.value = UiState.Success(userInfo)

    }

    fun getRecommendedList() {
        val recommendedList = {
            listOf<CertificationData>(
                CertificationData(),
                CertificationData(),
                CertificationData()
            )
        }

        _recommendedListLoadState.value = UiState.Success(recommendedList())

    }
    fun getPreCertList() {
        val preCertList = {
            listOf<CertificationData>(
                CertificationData(),
                CertificationData(),
                CertificationData()
            )
        }
        _preCertificationListLoadState.value = UiState.Success(preCertList())

    }
    fun getFavoriteList(isFavorite: Boolean) {
        val favoriteList = {
            listOf<CertificationData>(
                CertificationData(),
                CertificationData(),
                CertificationData()
            )
        }
        _favoriteListLoadState.value = UiState.Success(favoriteList())

    }

    fun onFavoriteClicked() {
        _isFavorite.value = !_isFavorite.value
    }



}

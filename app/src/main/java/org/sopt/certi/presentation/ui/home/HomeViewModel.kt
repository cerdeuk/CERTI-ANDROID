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

    private val _recommendedListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)

    private val _preCertificationListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)

    private val _favoriteListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)

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
        val userInfo = UserInfoData(
            name = "김서티",
            university = "솝트대학교",
            major = "경영학과",
            track = "인문계열",
            percentage = 57,
            category = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보")
        )
        _userInfoLoadState.value = UiState.Success(userInfo)
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
        val preCertList = {
            listOf<CertificationData>(
                CertificationData(
                    certificationId = 1,
                    certificationName = "시각디자인산업기사",
                    averagePeriod = "3개월",
                    nearestTestDate = "2025.05.27",
                    agencyName = "한국산업인력공단",
                    iconIndex = 0
                ),
                CertificationData(
                    certificationId = 2,
                    certificationName = "시각디자인산업기사",
                    averagePeriod = "3개월",
                    nearestTestDate = "2025.05.27",
                    agencyName = "한국산업인력공단",
                    iconIndex = 1
                ),
                CertificationData(
                    certificationId = 3,
                    certificationName = "시각디자인산업기사",
                    averagePeriod = "3개월",
                    nearestTestDate = "2025.05.27",
                    agencyName = "한국산업인력공단",
                    iconIndex = 2
                )
            )
        }
        _preCertificationListLoadState.value = UiState.Success(preCertList())
    }
    fun getFavoriteList(isFavorite: Boolean) {
        val favoriteList = {
            listOf<CertificationData>(
                CertificationData(
                    certificationId = 1,
                    certificationName = "정보처리기사",
                    testType = "실기형",
                    agencyName = "한국산업인력공단",
                    certificationType = "국가기술자격"
                ),
                CertificationData(
                    certificationId = 2,
                    certificationName = "시각디자인산업기사",
                    testType = "실기형",
                    agencyName = "한국산업인력공단",
                    certificationType = "국가기술자격"
                )
            )
        }
        _favoriteListLoadState.value = UiState.Success(favoriteList())
    }

    fun onFavoriteClicked(certificationId: Long) {
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

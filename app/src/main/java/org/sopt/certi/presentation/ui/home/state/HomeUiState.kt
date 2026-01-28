package org.sopt.certi.presentation.ui.home.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.PreCertDayData
import org.sopt.certi.domain.model.user.UserInfoData

data class HomeUiState(
    val userInfoLoadState: UiState<UserInfoData> = UiState.Loading,
    val recommendedListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val favoriteListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val preCertMonthLoadState: UiState<List<Int>> = UiState.Loading,
    val preCertDayLoadState: UiState<PreCertDayData> = UiState.Loading
) {
    val loadState: UiState<Unit>
        get() = when {
            userInfoLoadState is UiState.Loading &&
            recommendedListLoadState is UiState.Loading &&
            favoriteListLoadState is UiState.Loading &&
            preCertMonthLoadState is UiState.Loading &&
            preCertDayLoadState is UiState.Loading -> UiState.Loading

            userInfoLoadState is UiState.Failure ||
            recommendedListLoadState is UiState.Failure ||
            favoriteListLoadState is UiState.Failure ||
            preCertMonthLoadState is UiState.Failure ||
            preCertDayLoadState is UiState.Failure-> UiState.Failure("fail to load data")

            userInfoLoadState is UiState.Success &&
            recommendedListLoadState is UiState.Success &&
            favoriteListLoadState is UiState.Success &&
            preCertMonthLoadState is UiState.Success &&
            preCertDayLoadState is UiState.Success-> UiState.Success(Unit)

            else -> UiState.Loading
        }
}
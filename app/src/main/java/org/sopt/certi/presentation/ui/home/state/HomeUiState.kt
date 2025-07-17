package org.sopt.certi.presentation.ui.home.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData

data class HomeUiState(
    val userInfoLoadState: UiState<UserInfoData> = UiState.Loading,
    val recommendedListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val preCertificationListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val favoriteListLoadState: UiState<List<CertificationData>> = UiState.Loading
) {
    val loadState: UiState<Unit>
        get() = when {
            userInfoLoadState is UiState.Loading &&
                recommendedListLoadState is UiState.Loading &&
                preCertificationListLoadState is UiState.Loading &&
                favoriteListLoadState is UiState.Loading -> UiState.Loading

            userInfoLoadState is UiState.Failure ||
                recommendedListLoadState is UiState.Failure ||
                preCertificationListLoadState is UiState.Failure ||
                favoriteListLoadState is UiState.Failure -> UiState.Failure("fail to load data")

            userInfoLoadState is UiState.Success &&
                recommendedListLoadState is UiState.Success &&
                preCertificationListLoadState is UiState.Success &&
                favoriteListLoadState is UiState.Success -> UiState.Success(Unit)

            else -> UiState.Loading
        }
}

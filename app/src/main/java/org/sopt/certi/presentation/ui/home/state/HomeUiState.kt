package org.sopt.certi.presentation.ui.home.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData

data class HomeUiState(
    val userInfoLoadState: UiState<UserInfoData> = UiState.Loading,
    val recommendedListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val preCertificationListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val favoriteListLoadState: UiState<List<CertificationData>> = UiState.Loading,
    val isFavorite: Boolean = true
) {
    val loadState: UiState<Unit>
        get() {
            val loadStates = listOf(
                userInfoLoadState,
                recommendedListLoadState,
                preCertificationListLoadState,
                favoriteListLoadState
            )

            return when {
                loadStates.any { it is UiState.Loading } -> UiState.Loading
                loadStates.any { it is UiState.Failure } ->
                    loadStates.first { it is UiState.Failure } as UiState.Failure
                loadStates.all { it is UiState.Success } -> UiState.Success(Unit)
                else -> UiState.Empty
            }
        }
}

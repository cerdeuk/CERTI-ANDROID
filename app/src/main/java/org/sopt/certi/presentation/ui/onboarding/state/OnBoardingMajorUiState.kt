package org.sopt.certi.presentation.ui.onboarding.state

import org.sopt.certi.core.state.UiState

data class OnBoardingMajorUiState(
    val majorSearchText: String,
    val majorListLoadState: UiState<List<String>>,
    val submittedMajorSearchText: String
) {
    val loadState: UiState<Unit>
        get() = when (majorListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Init -> UiState.Init
            else -> UiState.Empty
        }
}

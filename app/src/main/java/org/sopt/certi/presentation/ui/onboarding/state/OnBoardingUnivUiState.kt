package org.sopt.certi.presentation.ui.onboarding.state

import org.sopt.certi.core.state.UiState

data class OnBoardingUnivUiState(
    val univSearchText: String,
    val univListLoadState: UiState<List<String>>,
    val submittedUnivSearchText: String
) {
    val loadState: UiState<Unit>
        get() = when (univListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Init -> UiState.Init
            else -> UiState.Empty
        }
}

package org.sopt.certi.presentation.ui.my.state

import org.sopt.certi.core.state.UiState

data class MyPageUnivUiState(
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

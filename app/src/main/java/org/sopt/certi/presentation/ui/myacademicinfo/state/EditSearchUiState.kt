package org.sopt.certi.presentation.ui.myacademicinfo.state

import org.sopt.certi.core.state.UiState

data class EditSearchUiState(
    val searchText: String = "",
    val searchListLoadState: UiState<List<String>> = UiState.Init,
    val submittedSearchText: String = ""
) {
    val loadState: UiState<Unit>
        get() = when (searchListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Init -> UiState.Init
            else -> UiState.Empty
        }

    val isSaveEnable: Boolean
        get() = submittedSearchText.isNotBlank()
}

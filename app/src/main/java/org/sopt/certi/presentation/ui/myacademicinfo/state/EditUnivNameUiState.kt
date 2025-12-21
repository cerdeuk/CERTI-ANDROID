package org.sopt.certi.presentation.ui.myacademicinfo.state

import org.sopt.certi.core.state.UiState

data class EditUnivNameUiState(
    val univSearchText: String = "",
    val univListLoadState: UiState<List<String>> = UiState.Init,
    val submittedUnivSearchText: String = "",
    val savedUnivName: String = ""
) {
    val loadState: UiState<Unit>
        get() = when (univListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Init -> UiState.Init
            else -> UiState.Empty
        }

    val isSaveEnable: Boolean
        get() = submittedUnivSearchText.isNotBlank() && submittedUnivSearchText != savedUnivName
}

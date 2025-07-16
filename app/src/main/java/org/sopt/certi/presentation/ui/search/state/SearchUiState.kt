package org.sopt.certi.presentation.ui.search.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData

data class SearchUiState(
    val searchCertificationListLoadState: UiState<List<CertificationData>>,
    val keyword: String,
    val submittedKeyword: String
) {
    val loadState: UiState<Unit>
        get() = when (searchCertificationListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Init -> UiState.Init
            else -> UiState.Empty
        }
}

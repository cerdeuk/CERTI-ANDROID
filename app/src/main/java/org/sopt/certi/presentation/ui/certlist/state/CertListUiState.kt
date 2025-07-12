package org.sopt.certi.presentation.ui.certlist.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData

data class CertListUiState(
    val certificationListLoadState: UiState<List<CertificationData>>,
    val selectedCategory: Int,
    val isFavorite: Boolean
) {
    val loadState: UiState<Unit>
        get() = when (certificationListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            else -> UiState.Empty
        }
}

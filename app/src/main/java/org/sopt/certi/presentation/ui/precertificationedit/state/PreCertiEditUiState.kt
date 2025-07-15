package org.sopt.certi.presentation.ui.precertificationedit.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData

data class PreCertiEditUiState(
    val preCertiListLoadState: UiState<List<CertificationData>>,
    val showDialog: Boolean,
    val selectedDeleteItem: Long? = null
) {
    val loadState: UiState<Unit>
        get() = when (preCertiListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            else -> UiState.Empty
        }
}

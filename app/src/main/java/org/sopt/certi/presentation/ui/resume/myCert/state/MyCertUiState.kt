package org.sopt.certi.presentation.ui.resume.myCert.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData

class MyCertUiState(
    val myCertListLoadState: UiState<List<CertificationData>>
) {
    val loadState: UiState<Unit>
        get() = when {
            myCertListLoadState is UiState.Success -> UiState.Success(Unit)
            else -> UiState.Empty
        }
}
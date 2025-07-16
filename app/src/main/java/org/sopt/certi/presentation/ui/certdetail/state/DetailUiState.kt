package org.sopt.certi.presentation.ui.certdetail.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData

data class DetailUiState(
    val detailCertificationLoadState: UiState<CertificationData>
) {
    val loadState: UiState<Unit>
        get() = when (detailCertificationLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Init -> UiState.Init
            is UiState.Failure -> UiState.Failure(detailCertificationLoadState.msg)
            else -> UiState.Empty
        }
}

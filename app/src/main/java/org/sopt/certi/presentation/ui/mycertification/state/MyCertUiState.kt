package org.sopt.certi.presentation.ui.mycertification.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.MyCertType

data class MyCertUiState(
    val selectedTab: MyCertType,
    val myCertListLoadState: UiState<List<CertificationData>>,
    val selectedCertificationId: Long?
) {
    val loadState: UiState<Unit>
        get() = when (myCertListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Failure -> UiState.Failure("fail to load data")
            is UiState.Loading -> UiState.Loading
            else -> UiState.Empty
        }
}

package org.sopt.certi.presentation.ui.mycertification.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.MyCertType

data class MyCertUiState(
    val isEditMode: Boolean = false,
    val selectedTab: MyCertType = MyCertType.PLANNED,
    val plannedCertListState: UiState<List<CertificationData>> = UiState.Loading,
    val acquiredCertListState: UiState<List<CertificationData>> = UiState.Loading,
    val favoriteCertListState: UiState<List<CertificationData>> = UiState.Loading,
    val editTargetCertification: CertificationData? = null,
    val deleteTargetId: Long? = null
) {
    val currentListState: UiState<List<CertificationData>>
        get() = when (selectedTab) {
            MyCertType.PLANNED -> plannedCertListState
            MyCertType.ACQUIRED -> acquiredCertListState
            MyCertType.FAVORITE -> favoriteCertListState
        }
}

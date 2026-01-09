package org.sopt.certi.presentation.ui.mycertification.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.MyCertType

data class MyCertUiState(
    val isEditMode: Boolean,
    val selectedTab: MyCertType,
    val myCertListLoadState: UiState<List<CertificationData>>,
    val editTargetCertification: CertificationData?,
    val deleteTargetId: Long?
)

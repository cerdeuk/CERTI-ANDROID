package org.sopt.certi.presentation.ui.resume.myCert.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData

data class MyCertUiState(
    val myCertListLoadState: UiState<List<CertificationData>>,
    val selectedCertificationId: Long?
)

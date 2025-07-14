package org.sopt.certi.presentation.ui.resume.siddeffect

import org.sopt.certi.domain.model.CertificationData

sealed interface ResumeSideEffect {
    data class ShowCertificationDetailModal(val data: CertificationData) : ResumeSideEffect
    data object HideCertificationDetailModal : ResumeSideEffect
}

package org.sopt.certi.presentation.ui.resume.main.sideEffect

import org.sopt.certi.domain.model.CertificationData

sealed interface ResumeSideEffect {
    data object ShowCertificationDetailModal: ResumeSideEffect
    data object HideCertificationDetailModal : ResumeSideEffect
}

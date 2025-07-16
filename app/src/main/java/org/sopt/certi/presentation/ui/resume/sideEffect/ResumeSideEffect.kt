package org.sopt.certi.presentation.ui.resume.sideEffect

sealed interface ResumeSideEffect {
    data object ShowCertificationDetailModal : ResumeSideEffect
}

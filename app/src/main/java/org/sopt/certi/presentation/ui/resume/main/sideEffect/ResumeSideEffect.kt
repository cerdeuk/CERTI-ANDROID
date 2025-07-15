package org.sopt.certi.presentation.ui.resume.main.sideEffect

sealed interface ResumeSideEffect {
    data object ShowCertificationDetailModal : ResumeSideEffect
}

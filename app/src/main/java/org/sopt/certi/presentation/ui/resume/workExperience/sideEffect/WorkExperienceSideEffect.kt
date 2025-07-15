package org.sopt.certi.presentation.ui.resume.workExperience.sideEffect

sealed interface WorkExperienceSideEffect {
    data object showDeleteDialog : WorkExperienceSideEffect
}
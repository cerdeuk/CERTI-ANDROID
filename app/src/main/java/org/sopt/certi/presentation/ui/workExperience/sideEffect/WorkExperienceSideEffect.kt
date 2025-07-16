package org.sopt.certi.presentation.ui.workExperience.sideEffect

sealed interface WorkExperienceSideEffect {
    data object showDeleteDialog : WorkExperienceSideEffect
}

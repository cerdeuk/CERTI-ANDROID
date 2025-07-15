package org.sopt.certi.presentation.ui.resume.activity.sideEffect

sealed interface ActivitySideEffect {
    data object showDeleteDialog : ActivitySideEffect
}

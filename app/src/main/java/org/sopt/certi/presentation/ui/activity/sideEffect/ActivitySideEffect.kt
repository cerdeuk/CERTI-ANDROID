package org.sopt.certi.presentation.ui.activity.sideEffect

sealed interface ActivitySideEffect {
    data object showDeleteDialog : ActivitySideEffect
}

package org.sopt.certi.presentation.ui.resume.myCert.sideEffect

sealed interface MyCertSideEffect {
    data object ShowDeleteDialog : MyCertSideEffect
}

package org.sopt.certi.presentation.ui.myCert.sideEffect

sealed interface MyCertSideEffect {
    data object ShowDeleteDialog : MyCertSideEffect
}

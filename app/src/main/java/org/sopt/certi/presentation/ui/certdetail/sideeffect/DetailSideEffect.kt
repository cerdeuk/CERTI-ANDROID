package org.sopt.certi.presentation.ui.certdetail.sideeffect

interface DetailSideEffect {
    data object ShowAcquireExpectFailToast : DetailSideEffect
    data object ShowAcquireExpectSuccessToast : DetailSideEffect
    data object ShowAcquiredFailToast : DetailSideEffect
    data object ShowAcquiredSuccessDialog : DetailSideEffect
}
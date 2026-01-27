package org.sopt.certi.presentation.ui.mypage.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.user.MyPageInfo

data class MyPageUiSate(
    val myPageInfoLoadState: UiState<MyPageInfo> = UiState.Loading
)

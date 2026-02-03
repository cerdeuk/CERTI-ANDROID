package org.sopt.certi.presentation.ui.mypage.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.user.CertificationCount
import org.sopt.certi.domain.model.user.MyPageInfo

data class MyPageUiSate(
    val myPageInfoLoadState: UiState<Unit> = UiState.Loading,
    val myPageInfo: MyPageInfo = MyPageInfo(
        nickname = "",
        email = "",
        profileImageUrl = "",
        jobs = emptyList(),
        certificationCount = CertificationCount(0, 0, 0)
    )
)

package org.sopt.certi.presentation.ui.mypage.state

// 나중에 서버 나오면 바뀔 예정
data class MyPageUiSate(
    val name: String,
    val email: String,
    val jobList: List<String>,
    val acquireExpectedCertCount: Int,
    val acquiredCertCount: Int,
    val favoriteCertCount: Int
)

package org.sopt.certi.domain.model.user

data class MyPageInfo(
    val nickname: String,
    val email: String,
    val profileImageUrl: String,
    val jobs: List<String>,
    val certificationCount: CertificationCount
)

data class CertificationCount(
    val planned: Int,
    val acquired: Int,
    val favorite: Int
)

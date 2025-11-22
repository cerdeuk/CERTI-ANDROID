package org.sopt.certi.domain.model.user

data class UserProfile(
    val name: String,
    val nickname: String,
    val email: String,
    val birth: String,
    val profileImageUrl: String?
)

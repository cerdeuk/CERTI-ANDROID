package org.sopt.certi.domain.model

data class UserAuth(
    val needSignUp: Boolean,
    val preSignupToken: String,
    val userInformation: UserInformationAuth
)

data class UserInformationAuth(
    val email: String,
    val nickname: String,
    val profileImageUrl: String
)

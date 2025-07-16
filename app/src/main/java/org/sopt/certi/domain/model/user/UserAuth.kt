package org.sopt.certi.domain.model.user

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

data class SignUpResult(
    val userId: Long,
    val nickName: String,
    val university: String,
    val trackType: String,
    val major: String,
    val jobs: List<String>,
    val jwtResponse: JwtResponse
)

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String
)

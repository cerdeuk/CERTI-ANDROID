package org.sopt.certi.domain.model.user

data class UserAuth(
    val needSignUp: Boolean,
    val preSignupToken: String,
    val userInformation: UserInformationAuth,
    val jwtResponse: JwtResponse? = null
)

data class UserInformationAuth(
    val socialId: String,
    val socialType: String,
    val email: String,
    val name: String?,
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

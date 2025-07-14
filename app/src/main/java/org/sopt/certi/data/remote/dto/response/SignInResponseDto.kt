package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("needSignUp")
    val needSignUp: Boolean,
    @SerialName("preSignupToken")
    val preSignupToken: String,
    @SerialName("userInformation")
    val userInformation: UserInformationDto
)

@Serializable
data class UserInformationDto(
    @SerialName("email")
    val email: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String
)

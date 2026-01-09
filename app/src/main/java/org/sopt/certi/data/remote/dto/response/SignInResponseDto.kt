package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("needSignUp")
    val needSignUp: Boolean,
    @SerialName("preSignupToken")
    val preSignupToken: String? = null,
    @SerialName("userInformation")
    val userInformation: UserInformationDto? = null,
    @SerialName("userId")
    val userId: Long? = null,
    @SerialName("nickName")
    val nickName: String? = null,
    @SerialName("tokenResponse")
    val tokenResponse: TokenResponseDto? = null
)

@Serializable
data class UserInformationDto(
    @SerialName("socialId")
    val socialId: String,
    @SerialName("socialType")
    val socialType: String,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String?,
    @SerialName("profileImageUrl")
    val profileImageUrl: String
)

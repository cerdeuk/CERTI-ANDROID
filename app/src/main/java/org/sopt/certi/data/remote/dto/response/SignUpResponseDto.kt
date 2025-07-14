package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("nickName")
    val nickName: Long,
    @SerialName("university")
    val university: Long,
    @SerialName("trackType")
    val trackType: String,
    @SerialName("major")
    val major: String,
    @SerialName("jobs")
    val jobs: List<String>,
    @SerialName("jwtResponse")
    val jwtResponse: TokenResponseDto
)

@Serializable
data class TokenResponseDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
)
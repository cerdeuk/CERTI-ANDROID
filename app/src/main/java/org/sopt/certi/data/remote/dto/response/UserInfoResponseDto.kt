package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponseDto(
    @SerialName("nickname")
    val name: String,
    @SerialName("university")
    val university: String,
    @SerialName("major")
    val major: String
)

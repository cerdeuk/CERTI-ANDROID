package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("socialType")
    val socialType: String
)

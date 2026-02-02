package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresignedResponseDto(
    @SerialName("preSignedURL")
    val preSignedURL: String,
    @SerialName("publicURL")
    val publicURL: String
)

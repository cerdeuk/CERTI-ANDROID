package org.sopt.certi.data.remote.dto.request.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterCommentRequestDto(
    @SerialName("content")
    val content: String,
    @SerialName("certificationId")
    val certificationId: Long
)

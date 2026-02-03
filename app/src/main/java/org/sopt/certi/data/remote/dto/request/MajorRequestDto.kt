package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MajorRequestDto(
    @SerialName("majorName")
    val majorName: String
)

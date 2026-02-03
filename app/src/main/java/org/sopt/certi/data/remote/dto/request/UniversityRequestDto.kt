package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityRequestDto(
    @SerialName("universityName")
    val universityName: String
)

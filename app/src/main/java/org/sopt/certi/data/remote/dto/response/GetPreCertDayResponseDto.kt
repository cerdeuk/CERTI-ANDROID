package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPreCertDayResponseDto(
    @SerialName("date")
    val date: String,
    @SerialName("certifications")
    val certifications: List<GetCertInfoResponseDto>
)
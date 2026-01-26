package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPreCertDayResponseDto(
    @SerialName("date")
    val date: String,
    @SerialName("items")
    val items: List<PreCertDayItemResponseDto>
)

@Serializable
data class PreCertDayItemResponseDto(
    @SerialName("userPreCertificationId")
    val userPreCertificationId: Int,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("certificationType")
    val certificationType: String,
    @SerialName("description")
    val description: String,
    @SerialName("location")
    val location: String,
    @SerialName("time")
    val time: String,
)
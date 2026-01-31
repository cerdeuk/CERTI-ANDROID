package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPreCertMonthResponseDto(
    @SerialName("year")
    val year: Int,
    @SerialName("month")
    val month: Int,
    @SerialName("days")
    val days: List<PreCertMonthDayItem>
)

@Serializable
data class PreCertMonthDayItem(
    @SerialName("day")
    val day: Int,
    @SerialName("count")
    val count: Int
)

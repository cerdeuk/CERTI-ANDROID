package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCareersResponseDto(
    @SerialName("careerDetailResponseList")
    val careerDetailResponseList: List<CareerDetailResponseDto>
)

@Serializable
data class CareerDetailResponseDto(
    @SerialName("careerId")
    val careerId: Long,
    @SerialName("startAt")
    val startAt: String,
    @SerialName("endAt")
    val endAt: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("place")
    val place: String
)

package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetActivityListResponseDto(
    @SerialName("activityDetailResponses")
    val activityDetailResponses: List<ActivityDetailResponseDto>
)

@Serializable
data class ActivityDetailResponseDto(
    @SerialName("activityId")
    val activityId: Long,
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

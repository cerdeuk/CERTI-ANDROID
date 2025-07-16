package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddActivityRequestDto(
    @SerialName("startAt")
    val startAt: String,
    @SerialName("endAt")
    val endAt: String,
    @SerialName("place")
    val place: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String
)

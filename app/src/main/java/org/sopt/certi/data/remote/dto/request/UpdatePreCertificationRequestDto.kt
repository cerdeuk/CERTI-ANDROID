package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePreCertificationRequestDto(
    @SerialName("testDate")
    val testDate: String,
    @SerialName("city")
    val city: String,
    @SerialName("state")
    val state: String
)

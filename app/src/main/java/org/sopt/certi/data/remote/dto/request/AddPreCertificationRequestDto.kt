package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddPreCertificationRequestDto(
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("city")
    val city: String?,
    @SerialName("state")
    val state: String?,
    @SerialName("testDate")
    val testDate: String?,
)
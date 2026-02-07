package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAcquisitionRequestDto(
    @SerialName("acquisitionDate")
    val acquisitionDate: String,
    @SerialName("grade")
    val grade: String?
)

package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAcquisitionDetailResponseDto(
    @SerialName("acquisitionId")
    val acquisitionId: Long,
    @SerialName("cardFrontImageUrl")
    val cardFrontImageUrl: String,
    @SerialName("cardBackImageUrl")
    val cardBackImageUrl: String,
    @SerialName("index")
    val index: Int,
    @SerialName("name")
    val name: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("description")
    val description: String,
    @SerialName("acquisitionDate")
    val acquisitionDate: String
)

package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAcquisitionListResponseDto(
    @SerialName("acquisitionListDetailResponses")
    val acquisitionListDetailResponses: List<AcquisitionResponseDto>
)

@Serializable
data class AcquisitionResponseDto(
    @SerialName("acquisitionId")
    val acquisitionId: Long,
    @SerialName("index")
    val index: Int,
    @SerialName("name")
    val name: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("cardFrontImageUrl")
    val cardFrontImageUrl: String,
    @SerialName("tags")
    val tags: List<String>
)

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
    @SerialName("certificationType")
    val certificationType: String,
    @SerialName("index")
    val index: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("cardFrontImageUrl")
    val cardFrontImageUrl: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("acquisitionDate")
    val acquisitionDate: String,
    @SerialName("grade")
    val grade: String?
)

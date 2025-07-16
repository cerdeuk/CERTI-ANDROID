package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.certi.data.remote.util.LocalDateSerializer
import java.time.LocalDate

@Serializable
data class AcquisitionListWrapperDto(
    @SerialName("getAcquisitionResponses")
    val getAcquisitionResponses: List<AcquisitionResponseDto>
)

@Serializable
data class AcquisitionResponseDto(
    @SerialName("acquisitionId")
    val acquisitionId: Long,
    @SerialName("index")
    val index: Int,
    @SerialName("name")
    val name: String,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("createdAt")
    val createdAt: LocalDate,
    @SerialName("cardFrontImageUrl")
    val cardFrontImageUrl: String,
    @SerialName("cardBackImageUrl")
    val cardBackImageUrl: String,
    @SerialName("tags")
    val tags: List<String>
)

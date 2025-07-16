package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMajorResponseDto(
    @SerialName("majorNameList")
    val majorNameList: List<String>
)
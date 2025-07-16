package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchUnivResponseDto(
    @SerialName("universityNameList")
    val universityNameList: List<String>
)

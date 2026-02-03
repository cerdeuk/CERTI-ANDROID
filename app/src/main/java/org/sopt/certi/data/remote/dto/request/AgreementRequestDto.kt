package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgreementRequestDto(
    @SerialName("isAgreed")
    val isAgreed: Boolean
)

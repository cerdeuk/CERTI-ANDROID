package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Top3CertListResponseDto(
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("rank")
    val rank: Int,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("certificationType")
    val certificationType: String
)

package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreCertListResponseDto(
    @SerialName("preCertificationList")
    val preCertificationList: List<PreCertificationSimple>
)

@Serializable
data class PreCertificationSimple(
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("averagePeriod")
    val averagePeriod: String,
    @SerialName("nearestTestDate")
    val nearestTestDate: String,
    @SerialName("agencyName")
    val agencyName: String,
    @SerialName("iconIndex")
    val iconIndex: Int
)

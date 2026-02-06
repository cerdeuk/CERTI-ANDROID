package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreCertListResponseDto(
    @SerialName("data")
    val data: List<PreCertificationSimple>
)

@Serializable
data class PreCertificationSimple(
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("preCertificationId")
    val preCertificationId: Long,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("certificationType")
    val certificationType: String,
    @SerialName("description")
    val description: String,
    @SerialName("averagePeriod")
    val averagePeriod: String,
    @SerialName("nearestTestDate")
    val nearestTestDate: String,
    @SerialName("agencyName")
    val agencyName: String,
    @SerialName("iconIndex")
    val iconIndex: Int,
    @SerialName("city")
    val city: String,
    @SerialName("state")
    val state: String,
    @SerialName("testDate")
    val testDate: String
)

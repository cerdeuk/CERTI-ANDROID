package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPreCertDayListResponseDto(
    @SerialName("date")
    val date: String,
    @SerialName("certifications")
    val certifications: List<PreCertDayItemResponseDto>?
)

@Serializable
data class PreCertDayItemResponseDto(
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("averagePeriod")
    val averagePeriod: String,
    @SerialName("charge")
    val charge: String,
    @SerialName("agencyName")
    val agencyName: String,
    @SerialName("testType")
    val testType: String,
    @SerialName("description")
    val description: String,
    @SerialName("applicationMethod")
    val applicationMethod: String,
    @SerialName("applicationUrl")
    val applicationUrl: String,
    @SerialName("expirationPeriod")
    val expirationPeriod: String,
    @SerialName("city")
    val city: String,
    @SerialName("state")
    val state: String,
    @SerialName("testDate")
    val testDate: String,
    @SerialName("isAcquired")
    val isAcquired: Boolean
)

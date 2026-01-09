package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CertListResponseDto(
    @SerialName("certificationSimpleList")
    val certificationSimpleList: List<CertificationSimple>
)

@Serializable
data class CertificationSimple(
    @SerialName("isFavorite")
    val isFavorite: Boolean,
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("certificationType")
    val certificationType: String,
    @SerialName("testType")
    val testType: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("description")
    val description: String
)

package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteListResponseDto(
    @SerialName("favoriteCertificationList")
    val favoriteCertificationList: List<FavoriteCertSimple>
)

@Serializable
data class FavoriteCertSimple(
    @SerialName("certificationId")
    val certificationId: Long,
    @SerialName("isFavorite")
    val isFavorite: Boolean,
    @SerialName("certificationName")
    val certificationName: String,
    @SerialName("testType")
    val testType: String,
    @SerialName("agencyName")
    val agencyName: String
)

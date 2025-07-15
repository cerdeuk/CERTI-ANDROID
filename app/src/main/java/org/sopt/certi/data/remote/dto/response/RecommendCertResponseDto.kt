package org.sopt.certi.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetRecommendCertResponseDto(
    @SerializedName("recommendationList")
    val recommendationList: List<RecommendCertInfoDto>
)

@Serializable
data class RecommendCertInfoDto(
    @SerializedName("certificationId")
    val certificationId: Long,
    @SerializedName("certificationName")
    val certificationName: String,
    @SerializedName("certificationType")
    val certificationType: String,
    @SerializedName("testType")
    val testType: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("recommendationScore")
    val recommendationScore: Int,
    @SerializedName("isFavorite")
    val isFavorite: Boolean
)

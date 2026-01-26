package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMyPageResponseDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImageURL")
    val profileImageURL: String,
    @SerialName("email")
    val email: String,
    @SerialName("jobResponse")
    val jobResponse: JobResponseDto,
    @SerialName("upCount")
    val upCount: Int,
    @SerialName("acCount")
    val acCount: Int,
    @SerialName("fCount")
    val fCount: Int
)

@Serializable
data class JobResponseDto(
    @SerialName("jobList")
    val jobList: List<String>
)
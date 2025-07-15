package org.sopt.certi.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponseDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("university")
    val university: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("percentage")
    val percentage: Int
)
package org.sopt.certi.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPersonalInfoResponseDto(
    @SerialName("nickName")
    val nickName: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("birthDate")
    val birthDate: String?,
    @SerialName("profileImageURL")
    val profileImageURL: String?
)

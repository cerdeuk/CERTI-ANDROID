package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutPersonalInfoRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("nickName")
    val nickName: String,
    @SerialName("birthDate")
    val birthDate: String,
    @SerialName("publicURL")
    val publicURL: String
)

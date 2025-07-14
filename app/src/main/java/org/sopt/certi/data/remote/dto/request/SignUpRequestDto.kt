package org.sopt.certi.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.certi.data.remote.dto.response.UserInformationDto

@Serializable
data class SignUpRequestDto(
    @SerialName("userInformation")
    val userInformation: UserInformationDto,
    @SerialName("university")
    val university: String,
    @SerialName("grade")
    val grade: String,
    @SerialName("track")
    val track: String,
    @SerialName("major")
    val major: String,
    @SerialName("jobs")
    val jobs: List<String>
)
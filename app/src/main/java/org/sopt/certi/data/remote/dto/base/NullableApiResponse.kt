package org.sopt.certi.data.remote.dto.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NullableApiResponse<T>(
    @SerialName("status")
    val status: Int,
    @SerialName("code")
    val code: String? = null,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T?
)

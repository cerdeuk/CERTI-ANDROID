package org.sopt.certi.data.remote.dto.request.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentListPageableRequestDto(
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("sort")
    val sort: List<String>
)

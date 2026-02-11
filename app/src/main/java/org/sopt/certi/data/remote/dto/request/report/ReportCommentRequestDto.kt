package org.sopt.certi.data.remote.dto.request.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportCommentRequestDto (
    @SerialName("content")
    val content: String,
    @SerialName("shouldBlockUser")
    val shouldBlockUser: Boolean
)
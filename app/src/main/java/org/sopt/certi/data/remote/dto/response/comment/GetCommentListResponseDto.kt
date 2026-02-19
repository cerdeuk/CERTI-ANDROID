package org.sopt.certi.data.remote.dto.response.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCommentListResponseDto(
    @SerialName("content")
    val content: List<CommentItemResponseDto>,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("totalElements")
    val totalElements: Int,
    @SerialName("isLast")
    val isLast: Boolean
)

@Serializable
data class CommentItemResponseDto(
    @SerialName("commentId")
    val commentId: Long,
    @SerialName("userId")
    val userId: Long,
    @SerialName("nickName")
    val nickName: String,
    @SerialName("content")
    val content: String,
    @SerialName("userMajor")
    val userMajor: String,
    @SerialName("userJob")
    val userJob: String,
    @SerialName("state")
    val state: String,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("createdTime")
    val createdTime: String,
    @SerialName("lastModifiedTime")
    val lastModifiedTime: String,
    @SerialName("isLike")
    val isLike: Boolean
)

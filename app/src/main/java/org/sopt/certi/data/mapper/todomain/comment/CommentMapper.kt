package org.sopt.certi.data.mapper.todomain.comment

import org.sopt.certi.data.remote.dto.response.comment.CommentItemResponseDto
import org.sopt.certi.data.remote.dto.response.comment.GetCommentListResponseDto
import org.sopt.certi.domain.model.comment.CommentData
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.type.CertStateType

fun GetCommentListResponseDto.toDomain(): CommentData {
    return CommentData(
        content = content.map { it.toDomain() },
        totalPages = totalPages,
        totalElements = totalElements,
        isLast = isLast
    )
}

fun CommentItemResponseDto.toDomain(): CommentItemData {
    return CommentItemData(
        commentId = commentId,
        userId = userId,
        nickName = nickName,
        content = content,
        userMajor = userMajor,
        userJob = userJob,
        state = CertStateType.fromStateName(state),
        createdTime = createdTime,
        lastModifiedTime = lastModifiedTime,
        isLike = isLike,
        likeCount = likeCount
    )
}

package org.sopt.certi.data.mapper.todto.comment

import org.sopt.certi.data.remote.dto.request.comment.CommentListPageableRequestDto
import org.sopt.certi.data.remote.dto.request.comment.RegisterCommentRequestDto
import org.sopt.certi.domain.model.comment.CommentListPageableRequest
import org.sopt.certi.domain.model.comment.RegisterCommentRequest

fun RegisterCommentRequest.toDto(): RegisterCommentRequestDto {
    return RegisterCommentRequestDto(
        content = content,
        certificationId = certificationId
    )
}

fun CommentListPageableRequest.toDto(): CommentListPageableRequestDto {
    return CommentListPageableRequestDto(
        page = page,
        size = size,
        commentSortType = commentSortType.name
    )
}

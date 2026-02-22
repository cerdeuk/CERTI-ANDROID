package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.CommentRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.comment.CommentListPageableRequestDto
import org.sopt.certi.data.remote.dto.request.comment.RegisterCommentRequestDto
import org.sopt.certi.data.remote.dto.response.comment.GetCommentListResponseDto
import org.sopt.certi.data.remote.service.CommentService
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(
    private val commentService: CommentService
) : CommentRemoteDataSource {
    override suspend fun getCommentList(certificationId: Long, pageable: CommentListPageableRequestDto): ApiResponse<GetCommentListResponseDto> =
        commentService.getCommentList(certificationId, page = pageable.page, size = pageable.size, sort = if (pageable.sort.isNotEmpty()) pageable.sort.toString() else null)

    override suspend fun registerComment(registerCommentRequest: RegisterCommentRequestDto): NullableApiResponse<Unit> =
        commentService.registerComment(registerCommentRequest)

    override suspend fun likeComment(commentId: Long): NullableApiResponse<Unit> =
        commentService.likeComment(commentId)

    override suspend fun deleteComment(commentId: Long): NullableApiResponse<Unit> =
        commentService.deleteComment(commentId)
}

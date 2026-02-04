package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.comment.CommentListPageableRequestDto
import org.sopt.certi.data.remote.dto.request.comment.RegisterCommentRequestDto
import org.sopt.certi.data.remote.dto.response.comment.GetCommentListResponseDto

interface CommentRemoteDataSource {
    suspend fun getCommentList(certificationId: Long, pageable: CommentListPageableRequestDto): ApiResponse<GetCommentListResponseDto>
    suspend fun registerComment(registerCommentRequest: RegisterCommentRequestDto): NullableApiResponse<Unit>
    suspend fun likeComment(commentId: Long): NullableApiResponse<Unit>
    suspend fun deleteComment(commentId: Long): NullableApiResponse<Unit>
}
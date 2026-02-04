package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.comment.CommentData
import org.sopt.certi.domain.model.comment.CommentListPageableRequest
import org.sopt.certi.domain.model.comment.RegisterCommentRequest

interface CommentRepository {
    suspend fun getCommentList(certificationId: Long, pageable: CommentListPageableRequest): Result<CommentData>
    suspend fun registerComment(registerCommentRequest: RegisterCommentRequest): Result<Unit>
    suspend fun likeComment(commentId: Long): Result<Unit>
    suspend fun deleteComment(commentId: Long): Result<Unit>
}
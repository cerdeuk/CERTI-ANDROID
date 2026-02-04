package org.sopt.certi.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.model.comment.RegisterCommentRequest

interface CommentRepository {
    suspend fun getCommentList(certificationId: Long, sort: List<String> = listOf("createdTime,desc")): Flow<PagingData<CommentItemData>>
    suspend fun registerComment(registerCommentRequest: RegisterCommentRequest): Result<Unit>
    suspend fun likeComment(commentId: Long): Result<Unit>
    suspend fun deleteComment(commentId: Long): Result<Unit>
}
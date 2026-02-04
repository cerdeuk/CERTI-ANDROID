package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.comment.toDomain
import org.sopt.certi.data.mapper.todto.comment.toDto
import org.sopt.certi.data.remote.datasource.CommentRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.domain.model.comment.CommentData
import org.sopt.certi.domain.model.comment.CommentListPageableRequest
import org.sopt.certi.domain.model.comment.RegisterCommentRequest
import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentRemoteDataSource: CommentRemoteDataSource
) : CommentRepository {
    override suspend fun getCommentList(certificationId: Long, pageable: CommentListPageableRequest): Result<CommentData> {
        return runCatching {
            commentRemoteDataSource.getCommentList(certificationId, pageable.toDto())
                .handleApiResponse()
                .getOrThrow()
                .toDomain()
        }
    }

    override suspend fun registerComment(registerCommentRequest: RegisterCommentRequest): Result<Unit> {
        return runCatching {
            commentRemoteDataSource.registerComment(registerCommentRequest.toDto())
                .handleNullableApiResponse()
                .getOrThrow()
        }
    }

    override suspend fun likeComment(commentId: Long): Result<Unit> {
        return runCatching {
            commentRemoteDataSource.likeComment(commentId)
                .handleNullableApiResponse()
                .getOrThrow()
        }
    }

    override suspend fun deleteComment(commentId: Long): Result<Unit> {
        return runCatching {
            commentRemoteDataSource.deleteComment(commentId)
                .handleNullableApiResponse()
                .getOrThrow()
        }
    }
}
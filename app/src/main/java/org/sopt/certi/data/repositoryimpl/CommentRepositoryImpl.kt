package org.sopt.certi.data.repositoryimpl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.certi.data.mapper.todomain.comment.toDomain
import org.sopt.certi.data.mapper.todto.comment.toDto
import org.sopt.certi.data.pagingsource.createPager
import org.sopt.certi.data.remote.datasource.CommentRemoteDataSource
import org.sopt.certi.data.remote.dto.request.comment.CommentListPageableRequestDto
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.model.comment.RegisterCommentRequest
import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentRemoteDataSource: CommentRemoteDataSource
) : CommentRepository {
    override suspend fun getCommentList(certificationId: Long, sort: List<String>): Flow<PagingData<CommentItemData>> {
        return createPager(
            limit = 12,
            initialLoadSize = 12,
            q = sort
        ) { page, limit, sortParam ->
            commentRemoteDataSource.getCommentList(
                certificationId,
                CommentListPageableRequestDto(
                    page = page,
                    size = limit,
                    sort = sortParam ?: listOf("likeCount,desc")
                )
            )
                .handleApiResponse()
                .getOrThrow()
                .content
                .map { it.toDomain() }
        }.flow
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
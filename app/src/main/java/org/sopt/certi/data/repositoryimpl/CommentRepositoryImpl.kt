package org.sopt.certi.data.repositoryimpl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.sopt.certi.data.mapper.todomain.comment.toDomain
import org.sopt.certi.data.mapper.todto.comment.toDto
import org.sopt.certi.data.pagingsource.createPager
import org.sopt.certi.data.remote.datasource.CommentRemoteDataSource
import org.sopt.certi.data.remote.dto.request.comment.CommentListPageableRequestDto
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.model.comment.RegisterCommentRequest
import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentRemoteDataSource: CommentRemoteDataSource
) : CommentRepository {

    private var _totalCommentCount = MutableStateFlow(0)

    override suspend fun getCommentList(certificationId: Long, sort: List<String>): Flow<PagingData<CommentItemData>> {
        return createPager(
            limit = 12,
            initialLoadSize = 12,
            q = sort
        ) { page, limit, sortParam ->
            val response = commentRemoteDataSource.getCommentList(
                certificationId,
                CommentListPageableRequestDto(
                    page = page,
                    size = limit,
                    sort = sortParam ?: sort
                )
            ).data.toDomain()

            _totalCommentCount.emit(response.totalElements)
            response.content
        }.flow
    }

    override fun getTotalCommentCount(): Flow<Int> {
        return _totalCommentCount
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

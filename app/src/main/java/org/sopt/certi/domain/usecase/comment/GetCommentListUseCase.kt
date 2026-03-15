package org.sopt.certi.domain.usecase.comment

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.repository.CommentRepository
import org.sopt.certi.presentation.type.CommentSortType
import javax.inject.Inject

class GetCommentListUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun getCommentList(certificationId: Long, commentSortType: CommentSortType): Flow<PagingData<CommentItemData>> {
        return commentRepository.getCommentList(certificationId, commentSortType)
    }

    fun getTotalCommentCount(): Flow<Int> {
        return commentRepository.getTotalCommentCount()
    }
}

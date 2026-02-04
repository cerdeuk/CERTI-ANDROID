package org.sopt.certi.domain.usecase.comment

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class GetCommentListUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(certificationId: Long, sort: List<String> = listOf("likeCount", "desc")): Pair<Flow<PagingData<CommentItemData>>, Int> {
        return commentRepository.getCommentList(certificationId, sort)
    }
}
package org.sopt.certi.domain.usecase.comment

import org.sopt.certi.domain.model.comment.CommentData
import org.sopt.certi.domain.model.comment.CommentListPageableRequest
import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class GetCommentListUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(certificationId: Long, pageable: CommentListPageableRequest): Result<CommentData> {
        return commentRepository.getCommentList(certificationId, pageable)
    }
}
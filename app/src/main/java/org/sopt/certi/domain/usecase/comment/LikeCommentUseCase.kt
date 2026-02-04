package org.sopt.certi.domain.usecase.comment

import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class LikeCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(commentId: Long): Result<Unit> {
        return commentRepository.likeComment(commentId)
    }
}
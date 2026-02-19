package org.sopt.certi.domain.usecase.comment

import org.sopt.certi.domain.model.comment.RegisterCommentRequest
import org.sopt.certi.domain.repository.CommentRepository
import javax.inject.Inject

class RegisterCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(registerCommentRequest: RegisterCommentRequest): Result<Unit> {
        return commentRepository.registerComment(registerCommentRequest)
    }
}

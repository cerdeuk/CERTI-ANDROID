package org.sopt.certi.domain.model.comment

data class RegisterCommentRequest(
    val content: String,
    val certificationId: Long
)
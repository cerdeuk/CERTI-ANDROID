package org.sopt.certi.domain.model.comment

data class CommentListPageableRequest(
    val page: Int,
    val size: Int,
    val sort: List<String>
)
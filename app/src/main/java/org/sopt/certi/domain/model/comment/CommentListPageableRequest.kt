package org.sopt.certi.domain.model.comment

import org.sopt.certi.presentation.type.CommentSortType

data class CommentListPageableRequest(
    val page: Int,
    val size: Int,
    val commentSortType: CommentSortType
)

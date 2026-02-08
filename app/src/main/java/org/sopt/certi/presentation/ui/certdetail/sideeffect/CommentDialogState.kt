package org.sopt.certi.presentation.ui.certdetail.sideeffect

sealed interface CommentDialogState {
    data object Hidden : CommentDialogState
    data class ShowDeleteCommentDialog(val commentId: Long) : CommentDialogState
}
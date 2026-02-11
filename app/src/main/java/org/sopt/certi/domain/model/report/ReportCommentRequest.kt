package org.sopt.certi.domain.model.report

data class ReportCommentRequest (
    val content: String,
    val shouldBlockUser: Boolean
)
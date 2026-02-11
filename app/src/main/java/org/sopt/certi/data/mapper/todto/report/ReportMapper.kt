package org.sopt.certi.data.mapper.todto.report

import org.sopt.certi.data.remote.dto.request.report.ReportCommentRequestDto
import org.sopt.certi.domain.model.report.ReportCommentRequest

fun ReportCommentRequest.toDto() : ReportCommentRequestDto {
    return ReportCommentRequestDto(
        content = this.content,
        shouldBlockUser = this.shouldBlockUser
    )
}
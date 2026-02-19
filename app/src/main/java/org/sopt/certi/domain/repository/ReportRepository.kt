package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.report.ReportCommentRequest

interface ReportRepository {
    suspend fun reportComment(certificationCommentId: Long, reportCommentRequest: ReportCommentRequest): Result<Unit>
}

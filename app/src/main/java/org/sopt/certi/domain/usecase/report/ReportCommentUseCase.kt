package org.sopt.certi.domain.usecase.report

import org.sopt.certi.domain.model.report.ReportCommentRequest
import org.sopt.certi.domain.repository.ReportRepository
import javax.inject.Inject

class ReportCommentUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke(certificationCommentId: Long, reportCommentRequest: ReportCommentRequest) : Result<Unit> =
        reportRepository.reportComment(certificationCommentId, reportCommentRequest)
}
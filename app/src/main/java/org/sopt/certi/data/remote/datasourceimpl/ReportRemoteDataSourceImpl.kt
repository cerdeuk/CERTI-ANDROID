package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.ReportRemoteDataSource
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.report.ReportCommentRequestDto
import org.sopt.certi.data.remote.service.ReportService
import javax.inject.Inject

class ReportRemoteDataSourceImpl @Inject constructor(
    private val reportService: ReportService
) : ReportRemoteDataSource {
    override suspend fun reportComment(certificationCommentId: Long, reportCommentRequestDto: ReportCommentRequestDto): NullableApiResponse<Unit> {
        return reportService.reportComment(certificationCommentId, reportCommentRequestDto)
    }
}

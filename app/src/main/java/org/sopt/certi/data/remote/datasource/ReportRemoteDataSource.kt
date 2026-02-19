package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.report.ReportCommentRequestDto

interface ReportRemoteDataSource {
    suspend fun reportComment(
        certificationCommentId: Long,
        reportCommentRequestDto: ReportCommentRequestDto
    ): NullableApiResponse<Unit>
}

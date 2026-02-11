package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.report.ReportCommentRequestDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReportService {
    @POST("/api/v1/report/comment/{certification_comment_id}")
    suspend fun reportComment(
        @Path("certification_comment_id") certificationCommentId: Long,
        @Body reportCommentRequestDto: ReportCommentRequestDto
    ): NullableApiResponse<Unit>
}
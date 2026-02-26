package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todto.report.toDto
import org.sopt.certi.data.remote.datasource.ReportRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.domain.model.report.ReportCommentRequest
import org.sopt.certi.domain.repository.ReportRepository
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportRemoteDataSource: ReportRemoteDataSource
) : ReportRepository {
    override suspend fun reportComment(certificationCommentId: Long, reportCommentRequest: ReportCommentRequest): Result<Unit> {
        return runCatching {
            reportRemoteDataSource.reportComment(certificationCommentId, reportCommentRequest.toDto())
                .handleNullableApiResponse()
                .getOrThrow()
        }
    }
}

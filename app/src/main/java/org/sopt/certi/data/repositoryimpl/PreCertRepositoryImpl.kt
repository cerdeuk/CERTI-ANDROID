package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.remote.datasource.PreCertRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.repository.PreCertRepository
import javax.inject.Inject

class PreCertRepositoryImpl @Inject constructor(
    private val preCertRemoteDataSource: PreCertRemoteDataSource
) : PreCertRepository {
    override suspend fun acquireExpectCert(
        certificationId: Long,
        city: String?,
        state: String?,
        testDate: String?
    ): Result<Boolean> = safeApiCall {
        preCertRemoteDataSource.acquireExpectCert(
            certificationId,
            city,
            state,
            testDate
        ).handleNullableApiResponse()
            .getOrThrow() == true
    }
}

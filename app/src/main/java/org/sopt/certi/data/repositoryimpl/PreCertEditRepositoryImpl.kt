package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.remote.datasource.PreCertEditRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.repository.PreCertEditRepository
import javax.inject.Inject

class PreCertEditRepositoryImpl @Inject constructor(
    private val preCertEditRemoteDataSource: PreCertEditRemoteDataSource
) : PreCertEditRepository {
    override suspend fun deletePreCertification(certificationId: Long): Result<Unit> = safeApiCall {
        preCertEditRemoteDataSource.deletePreCertification(certificationId)
            .handleNullableApiResponse()
            .getOrThrow()
    }
}

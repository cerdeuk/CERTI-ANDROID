package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.remote.datasource.PreCertEditDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.repository.PreCertEditRepository
import javax.inject.Inject

class PreCertEditRepositoryImpl @Inject constructor(
    private val preCertEditDataSource: PreCertEditDataSource
) : PreCertEditRepository {
    override suspend fun deletePreCertification(certificationId: Long): Result<Unit> = safeApiCall {
        preCertEditDataSource.deletePreCertification(certificationId)
            .handleNullableApiResponse()
            .getOrThrow()
    }

}
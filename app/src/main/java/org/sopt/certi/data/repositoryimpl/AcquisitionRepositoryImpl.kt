package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.repository.AcquisitionRepository
import javax.inject.Inject

class AcquisitionRepositoryImpl @Inject constructor(
    private val acquisitionRemoteDataSource: AcquisitionRemoteDataSource
) : AcquisitionRepository {
    override suspend fun acquiredCert(certificationId: Long): Result<Boolean> = safeApiCall {
        acquisitionRemoteDataSource.acquiredCert(certificationId)
            .handleNullableApiResponse()
            .getOrThrow() == true
    }
}

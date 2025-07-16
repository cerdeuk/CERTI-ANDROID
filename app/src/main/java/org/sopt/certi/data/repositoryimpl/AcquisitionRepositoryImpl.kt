package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.acquisition.toDomain
import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.AcquisitionRepository
import javax.inject.Inject

class AcquisitionRepositoryImpl @Inject constructor(
    private val acquisitionRemoteDataSource: AcquisitionRemoteDataSource
) : AcquisitionRepository {
    override suspend fun getAcquisitionList(): Result<List<CertificationData>> = safeApiCall {
        acquisitionRemoteDataSource.getAcquisitionList()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getAcquisitionDetail(acquisitionId: Long): Result<CertificationData> = safeApiCall {
        acquisitionRemoteDataSource.getAcquisitionDetail(acquisitionId)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}

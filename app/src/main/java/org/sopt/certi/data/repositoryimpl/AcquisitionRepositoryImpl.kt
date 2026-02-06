package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.acquisition.toDomain
import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.dto.request.AddAcquisitionRequestDto
import org.sopt.certi.data.remote.dto.request.UpdateAcquisitionRequestDto
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.AcquisitionRepository
import javax.inject.Inject

class AcquisitionRepositoryImpl @Inject constructor(
    private val acquisitionRemoteDataSource: AcquisitionRemoteDataSource
) : AcquisitionRepository {
    override suspend fun acquiredCert(certificationId: Long): Result<Boolean> = safeApiCall {
        acquisitionRemoteDataSource.acquiredCert(AddAcquisitionRequestDto(certificationId))
            .handleNullableApiResponse()
            .getOrThrow() == true
    }

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

    override suspend fun deleteAcquisition(acquisitionId: Long): Result<Unit> = safeApiCall {
        acquisitionRemoteDataSource.deleteAcquisition(acquisitionId)
            .handleNullableApiResponse()
            .getOrThrow()
    }

    override suspend fun updateAcquisition(acquisitionId: Long, acquisitionDate: String, grade: String): Result<Unit> = safeApiCall {
        acquisitionRemoteDataSource.updateAcquisition(
            acquisitionId = acquisitionId,
            request = UpdateAcquisitionRequestDto(
                acquisitionDate = acquisitionDate,
                grade = if (grade.isBlank()) null else grade
            )
        )
            .handleNullableApiResponse()
            .getOrThrow()
    }
}

package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.cert.toDomain
import org.sopt.certi.data.mapper.todomain.user.toDomain
import org.sopt.certi.data.remote.datasource.CertRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.CertificationListData
import org.sopt.certi.domain.repository.CertRepository
import javax.inject.Inject

class CertRepositoryImpl @Inject constructor(
    private val certRemoteDataSource: CertRemoteDataSource
) : CertRepository {
    override suspend fun getRecommendCertList(): Result<CertificationListData> = safeApiCall {
        certRemoteDataSource.getRecommendCertList()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getCertInfo(certificationId: Long): Result<CertificationData> = safeApiCall {
        certRemoteDataSource.getCertInfo(certificationId)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}

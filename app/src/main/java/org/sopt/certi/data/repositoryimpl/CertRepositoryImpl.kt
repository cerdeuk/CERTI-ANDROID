package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.cert.toDomain
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

    override suspend fun searchCertList(keyword: String): Result<List<CertificationData>> = safeApiCall {
        certRemoteDataSource.searchCertList(keyword)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getJobCertList(isFavorite: Boolean, jobs: String): Result<List<CertificationData>> = safeApiCall {
        certRemoteDataSource.getCategoryCertList(isFavorite, jobs)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getTrackCertList(isFavorite: Boolean, tracks: String): Result<List<CertificationData>> = safeApiCall {
        certRemoteDataSource.getTrackCertList(isFavorite, tracks)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getTop3TrackCertList(): Result<List<CertificationData>> = safeApiCall {
        certRemoteDataSource.getTop3TrackCertList()
            .handleApiResponse()
            .getOrThrow()
            .map { it.toDomain() }
    }

    override suspend fun getTop3JobCertList(): Result<List<CertificationData>> = safeApiCall {
        certRemoteDataSource.getTop3JobCertList()
            .handleApiResponse()
            .getOrThrow()
            .map { it.toDomain() }
    }
}

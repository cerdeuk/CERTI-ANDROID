package org.sopt.certi.data.repositoryimpl
import org.sopt.certi.data.mapper.todomain.user.toDomain
import org.sopt.certi.data.mapper.todomain.cert.toDomain
import org.sopt.certi.data.remote.datasource.CertRemoteDataSource
import org.sopt.certi.data.remote.datasource.HomeRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val certRemoteDataSource: CertRemoteDataSource
) : HomeRepository {

    override suspend fun getUserInfo(): Result<UserInfoData> {
        return runCatching {
            homeRemoteDataSource.getUserInfo()
                .handleApiResponse()
                .getOrThrow()
                .toDomain()
        }
    }

    override suspend fun getRecommendedCertList(): Result<List<CertificationData>> {
        return runCatching {
            certRemoteDataSource.getRecommendCertList()
                .handleApiResponse()
                .getOrThrow()
                .toDomain()
                .certificationList
        }
    }

    override suspend fun getPreCertificationList(): Result<List<CertificationData>> {
        return runCatching {
            homeRemoteDataSource.getPreCertificationList()
                .handleNullableApiResponse()
                .getOrThrow()
                ?.toDomain() ?: emptyList()
        }
    }

    override suspend fun getFavoriteList(): Result<List<CertificationData>> {
        return runCatching {
            homeRemoteDataSource.getFavoriteList()
                .handleNullableApiResponse()
                .getOrThrow()
                ?.toDomain() ?: emptyList()
        }
    }

    override suspend fun toggleFavorite(certificationId: Long): Result<Unit> {
        return runCatching {
            homeRemoteDataSource.toggleFavorite(certificationId)
            Unit
        }
    }
}

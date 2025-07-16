package org.sopt.certi.data.repositoryimpl
import org.sopt.certi.data.mapper.todomain.user.toDomain
import org.sopt.certi.data.mapper.todomain.cert.toDomain
import org.sopt.certi.data.remote.datasource.HomeRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
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
            homeRemoteDataSource.getRecommendedCertList()
                .handleApiResponse()
                .getOrThrow()
                .toDomain()
                .certificationList
        }
    }

    override suspend fun getPreCertificationList(): Result<List<CertificationData>> {
        return runCatching {
            homeRemoteDataSource.getPreCertificationList()
                .handleApiResponse()
                .getOrThrow()
                .toDomain()
        }
    }

    override suspend fun getFavoriteList(): Result<List<CertificationData>> {
        return runCatching {
            homeRemoteDataSource.getFavoriteList()
                .handleApiResponse()
                .getOrThrow()
                .toDomain()
        }
    }

    override suspend fun toggleFavorite(certificationId: Long): Result<Unit> {
        return runCatching {
            homeRemoteDataSource.toggleFavorite(certificationId)
            Unit
        }
    }
}

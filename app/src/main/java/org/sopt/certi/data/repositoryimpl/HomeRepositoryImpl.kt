package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.remote.datasource.HomeRemoteDataSource
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {

    override suspend fun getUserInfo(): Result<UserInfoData> {
        return runCatching {
            val response = homeRemoteDataSource.getUserInfo()
            response.data.toDomain()
        }
    }

    override suspend fun getPreCertificationList(): Result<List<CertificationData>> {
        return runCatching {
            val response = homeRemoteDataSource.getPreCertificationList()
            response.data.data.map { it.toDomain() }
        }
    }

    override suspend fun getFavoriteList(): Result<List<CertificationData>> {
        return runCatching {
            val response = homeRemoteDataSource.getFavoriteList()
            response.data.data.map { it.toDomain() }
        }
    }
}

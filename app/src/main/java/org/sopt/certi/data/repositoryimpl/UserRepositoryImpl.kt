package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.toDomain
import org.sopt.certi.data.remote.datasource.UserRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.InterestedJobListData
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun getInterestedJobList(): Result<InterestedJobListData> = safeApiCall {
         userRemoteDataSource.getInterestedJobList()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}
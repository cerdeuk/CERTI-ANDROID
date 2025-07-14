package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.toDomain
import org.sopt.certi.data.remote.datasource.AuthRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.UserAuth
import org.sopt.certi.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun signIn(accessToken: String, socialType: String): Result<UserAuth> = safeApiCall {
        authRemoteDataSource.signIn(accessToken = accessToken, socialType = socialType)
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}

package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.auth.toDomain
import org.sopt.certi.data.remote.datasource.AuthRemoteDataSource
import org.sopt.certi.data.remote.dto.request.SignUpRequestDto
import org.sopt.certi.data.remote.dto.response.UserInformationDto
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.user.SignUpResult
import org.sopt.certi.domain.model.user.UserAuth
import org.sopt.certi.domain.model.user.UserInformationAuth
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

    override suspend fun signUp(
        preSignupToken: String,
        userInformation: UserInformationAuth?,
        nickname: String,
        university: String,
        grade: String,
        track: String,
        major: String,
        jobs: List<String>
    ): Result<SignUpResult> = safeApiCall {
        val userInfoDto = UserInformationDto(
            socialId = userInformation?.socialId ?: "",
            socialType = userInformation?.socialType ?: "",
            email = userInformation?.email ?: "",
            name = userInformation?.name ?: "",
            profileImageUrl = userInformation?.profileImageUrl ?: ""
        )
        val signUpRequestDto = SignUpRequestDto(
            userInformation = userInfoDto,
            university = university,
            grade = grade,
            track = track,
            major = major,
            jobs = jobs
        )
        authRemoteDataSource.signUp(
            accessToken = "Bearer $preSignupToken",
            signUpRequest = signUpRequestDto
        ).handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun searchUniv(keyword: String): Result<List<String>> = safeApiCall {
        authRemoteDataSource.searchUniv(keyword = keyword)
            .handleApiResponse()
            .getOrThrow()
            .universityNameList
    }

    override suspend fun searchMajor(keyword: String): Result<List<String>> = safeApiCall {
        authRemoteDataSource.searchMajor(keyword = keyword)
            .handleApiResponse()
            .getOrThrow()
            .majorNameList
    }

    override suspend fun withDraw(): Result<Unit> = safeApiCall {
        authRemoteDataSource.withDraw()
            .handleNullableApiResponse()
            .getOrThrow()
    }
}
